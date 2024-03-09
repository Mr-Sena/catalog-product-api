<h1 align = center > Catalog Product API </h1>

<p align="center">
<img src="https://img.shields.io/static/v1?label=GitHub Profile:&message=@Mr-Sena&color=8257E5&labelColor=000000" alt="@Mr-Sena">
<img src="https://img.shields.io/static/v1?label=Tipo&message=Blueprint&color=8257E5&labelColor=000000" alt="Blueprint">
</p>

Esse sistema representa o desenvolvimento de uma Api realizada, para gerenciar um sistema de catálogo de produtos.


## Links Reference:

 - AWS Datacenter Config: [Terraform infra as code](https://github.com/Mr-Sena/catalog-product-infra-terraform)
 - Serverless Consumer Repository: [Consumer-Lambda-Catalog](https://github.com/Mr-Sena/Consumer-Lambda-Catalog) - The same origin of the [*index.mjs*](./index.mjs) file.
 

 <br>
 

## Fluxograma De Arquitetura
<p align="center"> <img src = Fluxograma.png align = center> </p>


<br>

## Tecnologias de Desenvolvimento: 
 - Spring Boot;
 - Spring Web;
 - Docker container;
 - MongoDB;
 - Lombok;
 - AWS SQS;
 - AWS SNS;
 - AWS Lambda with Node.js;
 - AWS Bucket S3.

## Técnicas Realizadas: 

 - API Rest.
 - Arquitetura Hexagonal.
 - Tecnologia de isolamento e abstração de recursos de hardware (Docker container);


## Procedimentos para o executar o aplicativo: 

1. Executar o container para criar a base de dados: 

```
 > docker compose up
```

2. Executar o aplicativo.
```
 > mvn spring-boot:run
```


 * Provisionamento AWS Service disponível: [Terraform infra as code](https://github.com/Mr-Sena/catalog-product-infra-terraform)



## Api Endpoints


### Api Category Template

- Adicionar nova categoria.

```
http POST :8080/api/category


Body (json):  
{
    "title": "Load the database.",
    "desc": "New item.",
    "ownerId": "19"
}
```



- Lista de categorias

```
http GET :8080/api/category
```



- Atualizar categoria

```
http UPDATE :8080/api/category/<category-id>

Body (json):
{
    "title": "AWS Datacenter",
    "desc": "Verified.",
    "ownerId": "19"
}

```



- Excluir uma categoria.

```
http DELETE :8080/api/category/<category-id>
```


<br><br>



### Api Product Template

- Adicionar um novo produto.

```
http POST :8080/api/product


Body (json):  
{
    "title": "Registro para cadastrar o produto.",
    "desc": "Cadastro para o item.",
    "ownerId": "11",
    "categoryId": "65de6c5e56c0ab784c2b90f5",
    "price": 230
}
```



- Lista de produtos.

```
http GET :8080/api/product
```



- Atualizar produto

```
http UPDATE :8080/api/product/<product-id>

Body (json):
{
    "title": "Registro para cadastrar o produto.",
    "desc": "Cadastro para o item.",
    "ownerId": "11",
    "categoryId": "65de6c5e56c0ab784c2b90f5",
    "price": 225
}

```



- Excluir um produto.

```
http DELETE :8080/api/product/<product-id>
```
