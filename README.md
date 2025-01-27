# Sobre o desafio

Nesse desafio você desenvolverá uma API fictícia para uma empresa de cursos de programação, onde em um primeiro momento, você deverá utilizar o CRUD, para criação de cursos.

A API deve conter as seguintes funcionalidades:

- [x] Criação de um novo curso
- [x] Listagem de todos os cursos
- [x] Atualização de um curso pelo `id`
- [x] Remover um curso pelo `id`
- [x] Alternar o status de active de um curso pelo `id`

## Rotas e regras de negócio

Antes das rotas, vamos entender qual a estrutura (propriedades) que uma task deve ter:

- [x] `id` - Identificador único de cada curso
- [x] `name` - Nome do curso
- [x] `category` - Categoria do curso
- [x] `active` - Define se o curso está ativo ou não
- [x] `createdAt` - Data de quando o curso foi criado.
- [x] `updatedAt` - Deve ser sempre alterado para a data de quando o curso for atualizada.

Rotas:

- [x] `POST - /cursos`
    Deve ser possível criar um curso no banco de dados, enviando os campos `name` e `category` por meio do `body` da requisição.
    Ao criar um curso, os campos: `id`, `created_at`   e `updated_at` devem ser preenchidos automaticamente, conforme a orientação das propriedades acima.
- [x] `GET - /cursos`
    Deve ser possível listar todas os cursos salvos no banco de dados.
    Também deve ser possível realizar uma busca, filtrando os cursos pelo `name` e `category`
- [x] `PUT - /cursos/:id`
    Deve ser possível atualizar um curso pelo `id`.
    No `body` da requisição, deve receber somente o `name` e/ou `category` para serem atualizados.
    Se for enviado somente o `name`, significa que o `category` não pode ser atualizado e vice-versa. Além disso `active` for informado nessa rota, ele deverá ser ignorado, pois a rota que deverá fazer essa atualização, é a de patch.
- [x] `DELETE - /cursos/:id`
    Deve ser possível remover um curso pelo `id`.
- [x] `PATCH - /cursos/:id/active`
    Essa rota servirá para marcar se o curso está ativo ou não, ou seja, um toggle entre true or false.

### Dica

A anotação `@CreationTimestamp` define que o valor do atributo `createdAt` será definido automaticamente pelo banco de dados no momento da criação da entidade. A anotação `@UpdateTimestamp` define que o valor do atributo `updatedAt` será definido automaticamente pelo banco de dados no momento da atualização da entidade.

## Indo além

Algumas sugestões do que pode ser implementado:

- Validar se as propriedades `name` e `category` das rotas `POST` e `PUT` estão presentes no `body` da requisição.
- Para a parte de definição se o curso está ativo ou não, você pode definir um ENUM(enumerador) para fazer esse ‘’check’’.   Sugestão de leitura: [clique aqui](https://www.devmedia.com.br/enums-no-java/38764).
- Para tratar as exceções, você pode se desafiar e criar as excpetion  😃
