# Consulta API e Salva (STAR WARS)

### OBJETIVO:

Receber um endpoint da API de Star Wars (https://swapi.dev/) relacionado a um personagem e salvar em um banco de dados o personagem retornado, usando o JDBC, Postgres e biblioteca Gson do Java.

### Preparação

1. Ter uma conexão pronta com o Postgres via JDBC;
2. Fazer o *import* da dependência “Gson” (via Maven) para o seu projeto;
3. Ter uma tabela com os mesmos atributos que serão recebidos no banco;
4. Criar uma classe “Personagem” para armazenar os dados vindo da API e salvar no banco;

### Executando
#### Rodar a classe "main";
#### Inserir um endpoint da API https://swapi.dev/ que seja de algum personagem, dentro da caixa de diálogo. Exemplos:
```
https://swapi.dev/api/people/1/ (Luke Skywalker)
https://swapi.dev/api/people/3/ (R2-D2)
https://swapi.dev/api/people/9/ (Biggs Darklighter)

Lista de personagens em: https://swapi.dev/api/people/

``` 
#### Verificar que no banco será salvo o personagem;
#### Caso já exista, irá mostrar uma mensagem e encerrar o programa.




