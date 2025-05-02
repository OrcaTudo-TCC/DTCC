
const endpoint = "http://localhost:8080/item-carrinho";

async function getItens(idCarrinho) {
    try {
        if(typeof idCarrinho !== 'number' || !Number.isInteger(idCarrinho)){
            throw new Error("O id do carrinho precisa ser um Inteiro");
        }
        const response = await fetch(endpoint+ "/" + idCarrinho,{
            method: "GET",
            headers: { "Content-Type": "application/json" }
        })
        if(!response.ok){
            throw new Error("Erro na requisição: "+ response.status);
        }
        const data = await response.json();

        return data;

    } catch (error) {
        console.log("erro no método get itens: "+ error);
    }
}

/* Atualiza o item-carrinho com as informações passada pelo array
    
    o objeto deve conter:
    {
        "idDoItem": 0,
        "quantidade": 0,
        "nomeDoProduto": "string",
        "idDoCarrinho": 0
    }
*/
async function putItem(requestBody) {
    try {
        const camposObrigatorios = [
            'idDoItem',
            'quantidade',
            'nomeDoProduto',
            'idDoCarrinho'
          ];
        if (typeof requestBody !== 'object' || requestBody === null) {
            throw new Error("Parâmetro inválido: não é um objeto.");
        }
        const todosPresentes = camposObrigatorios.every(campo => campo in requestBody);
        if (!todosPresentes) {
            throw new Error("corpo da requisição incompleto. Faltando algum campo.");
        }
        const response = await fetch(endpoint,{
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                "idDoItem": requestBody.idDoItem,
                "quantidade": requestBody.quantidade,
                "nomeDoProduto": requestBody.nomeDoProduto,
                "idDoCarrinho": requestBody.idDoCarrinho
            })
        });
        if(!response.ok){
            throw new Error("Erro na requsição: "+ response.status)
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.log("Erro no método put item: "+ error)
    }

}

async function postItem(requestBody) {
    try {
        const camposObrigatorios = [
            'quantidade',
            'nomeDoProduto',
            'idDoCarrinho'
          ];
        if (typeof requestBody !== 'object' || requestBody === null) {
            throw new Error("Parâmetro inválido: não é um objeto.");
        }
        const todosPresentes = camposObrigatorios.every(campo => campo in requestBody);
        if (!todosPresentes) {
            throw new Error("corpo da requisição incompleto. Faltando algum campo.");
        }
        const response = await fetch(endpoint,{
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                "quantidade": requestBody.quantidade,
                "nomeDoProduto": requestBody.nomeDoProduto,
                "idDoCarrinho": requestBody.idDoCarrinho
            })
        });
        if(!response.ok){
            throw new Error("Erro na requsição: "+ response.status)
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.log("Erro no método post item: "+ error)
    }
}

// deleta o item-carrinho pelo seu id
async function deleteItem(idItem) {
    try {
        if(typeof idItem !== 'number' || !Number.isInteger(idItem)){
            throw new Error("O id do item-carrinho precisa ser um Inteiro");
        }
        const response = await fetch(endpoint + "/"+ idItem,{
            method: "DELETE",
            headers: { "Content-Type": "application/json" }
        })
        if(!response.ok){
            throw new Error("Erro na requsição: "+ response.status)
        }
        return true;
    } catch (error) {
        console.log("Erro no método delete item: "+ error);
    }
}