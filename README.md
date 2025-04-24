<h1 align="center">TCC EQUIPE - 03 Etec Uirapuru - Desenvolvimento de Sistemas</h1>
<h2 align="center">projeto: OrçaTudo</h2>
<p>O projeto ORÇATUDO tem como objetivo ser um sistema web que facilita a cotação e licitação de materiais de contrução e ferramentas</p>
<h3>Tecnologias utilizadas: HTML,CSS,Javascript,Java Com Spring Boot</h3>


<h4>Equipe:</h4>

-  **Murilo Miranda Verçosa**

-  **João Pedro Xavier**

-  **Otávio Franklin**

-  **Alex Alves da Silva**

-  **João Pedro Menezes**

 <h3 align="center">Foco é a ponte entre seus sonhos e suas conquistas</h3>
 <hr>

<h3>Modelo de Entidade Relacionamento lógico</h3>

```mermaid
erDiagram

    carrinho {
        int id PK
        int id_usuario
        boolean status
    }

    categoria {
        int id PK
        varchar nome
    }

    fornecedor {
        int id_fornecedor PK
        int role_id
        int avaliacao
        varchar area_de_atuacao
        varchar nome
        varchar razao_social
        varchar descricao
        varchar email
        varchar senha
        varchar cpf_cnpj
        varchar endereco
        varchar telefone
    }

    item_carrinho {
        int id PK
        int id_carrinho
        int id_produto
        int quantidade
    }

    licitacao {
        int id PK
        int id_fornecedor
        int id_produto
        int id_usuario
        boolean status
        float valor_final
        datetime data_fim
        datetime data_inicio
    }

    operacao {
        int id PK
        int id_usuario
        datetime data
        enum status
        enum tipo_operacao
    }

    pedido {
        int id PK
        int id_carrinho
        datetime data
    }

    produto {
        int id PK
        int id_fornecedor
        int id_subcategoriafinal
        float preco
        varchar descricao
        varchar nome
        longblob imagem
    }

    roles {
        int id PK
        datetime created_at
        datetime updated_at
        varchar description
        enum name
    }

    subcategoria {
        int id PK
        int id_categoria
        varchar nome
    }

    subcategoriafinal {
        int id PK
        int id_subcategoria
        varchar nome
    }

    usuarios {
        int id_usuario PK
        int role_id
        varchar telefone
        varchar cpf_cnpj
        varchar email
        varchar senha
        varchar endereco
        varchar nome
    }

    %% RELACIONAMENTOS
    carrinho ||--o{ usuarios : "id_usuario"
    item_carrinho ||--o{ carrinho : "id_carrinho"
    item_carrinho ||--o{ produto : "id_produto"
    licitacao ||--o{ fornecedor : "id_fornecedor"
    licitacao ||--o{ produto : "id_produto"
    licitacao ||--o{ usuarios : "id_usuario"
    operacao ||--o{ usuarios : "id_usuario"
    pedido ||--o{ carrinho : "id_carrinho"
    produto ||--o{ fornecedor : "id_fornecedor"
    produto ||--o{ subcategoriafinal : "id_subcategoriafinal"
    subcategoria ||--o{ categoria : "id_categoria"
    subcategoriafinal ||--o{ subcategoria : "id_subcategoria"
    usuarios ||--o{ roles : "role_id"
    fornecedor ||--o{ roles : "role_id"

```



