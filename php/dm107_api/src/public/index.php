<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

require '../vendor/autoload.php';
$config['displayErrorDetails'] = true;
$config['addContentLengthHeader'] = false;

$config['db']['host'] = "localhost";
$config['db']['user'] = "root";
$config['db']['pass'] = "";
$config['db']['dbname'] = "dm107";

//Inicialização do Slim
$app = new \Slim\App(["config" => $config]);

$username = $_SERVER["PHP_AUTH_USER"];
$password = $_SERVER["PHP_AUTH_PW"];
$container = $app->getContainer();

$container['db'] = function ($c) {
    $dbConfig = $c['config']['db'];
    $pdo = new PDO("mysql:host=". $dbConfig['host'] . ";dbname=" . $dbConfig['dbname'], $dbConfig['user'], $dbConfig['pass']);
    $pdo->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);
    $pdo->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE,PDO::FETCH_ASSOC);
    $db = new NotORM($pdo);
    return $db;
};

$usuarios = $container['db']->usuarios->where("user = ?", $username)->where("senha = ?", $password);
$res = array();
$res["user"] = $res["senha"] = "";

foreach ($usuarios as $usuario) {
    $res["user"] = $usuario["user"];
    $res["senha"] = $usuario["senha"];
}

// Autentica
$app->add(new Tuupola\Middleware\HttpBasicAuthentication([

    "users" => [
        $res["user"] => $res["senha"]
    ],
    "error" => function ($request, $response, $arguments) {
        $data["status"] = "error";
        $data["message"] = $arguments["message"];
        return $response
            ->withHeader("Content-Type", "application/json")
            ->write(json_encode($data, JSON_UNESCAPED_SLASHES | JSON_PRETTY_PRINT));
    }
]));

// Teste de autenticação
// $app->get("/api/{nome}", function (Request $request, Response $response) {
//     $nome = $request->getAttribute("nome");
//     $response->getBody()->write("Bem vindo a API! Testando autenticação, $nome");
//     return $response;
// });

//Atualiza a entrega especifica
$app->put('/api/entrega/{id}', function (Request $request, Response $response) {

    $id = $request->getAttribute('id');
    $body = $request->getParsedBody();

    $entrega = $this->db->entrega[$id];

    if ($entrega) {
        $data = array(
            "numero_pedido" => $body["numeroPedido"],
            "id_cliente" =>  $body["idCliente"],
            "nome_recebedor" =>  $body["nomeRecebedor"],
            "cpf_recebedor" =>  $body["cpfRecebedor"],
            "data_entrega" => $body["dataEntrega"]
        );
        $result = $entrega->update($data);

        return "Entrega atualizada!";

    } else {

        return "Entrega não existe!";

    }
   
});

//Delete a entrega
$app->delete('/api/entrega/{id}', function (Request $request, Response $response) {

    $id = $request->getAttribute('id');

    $entrega = $this->db->entrega[$id];

    if ($entrega && $entrega->delete()) {
        return "Entrega deletada com sucesso!";
    } else {
        return "Entrega não existe!";
    }
});

// Busca todas as entregas
$app->get('/api/entrega', function (Request $request, Response $response) {

    $entrega = $this->db->entrega();
    return $response->withJson($entrega);
});

//Cadastra nova entrega
$app->post('/api/entrega/', function (Request $request, Response $response) {

    $body = $request->getParsedBody();

    $entrega = array(
        "numero_pedido" => $body["numeroPedido"],
        "id_cliente" =>  $body["idCliente"],
        "nome_recebedor" =>  $body["nomeRecebedor"],
        "cpf_recebedor" =>  $body["cpfRecebedor"],
        "data_entrega" => $body["dataEntrega"]
    );
    $result = $this->db->entrega()->insert($entrega);
    return "Entrega cadastrada!";

});

//Executa
$app->run();
?>