
const endpointCarrinho = "http://localhost:8080/carrinho";

async function getCarrinho(idUsuario) {
    try{
        if(typeof idUsuario !== 'number' || !Number.isInteger(idUsuario)){
            throw new Error("O id do usuário precisa ser um inteiro");
        }

        const response = await fetch(endpointCarrinho + "/" + idUsuario,{
            method: "GET",
            headers: { "Content-Type": "application/json" }
        })
        if(!response.ok){
            throw new Error("Erro na requisição"+response.status)
        }
        const data = await response.json();

        return data;

    }catch(err){
        console.log("Erro no método get carrinho: "+ err)
        return;
    }
}

async function postCarrinho(emailUsuario) {
    try {
        if(typeof emailUsuario !== 'string'){
            throw new Error("O email do usuário precisa ser uma string");
        }

        const response = await fetch(endpointCarrinho+ "/"+ emailUsuario,{
            method: "POST",
            headers: { "Content-Type": "application/json" }
        })
        if (!response.ok) {
            throw new Error(response.status);
        }
        const data = await response.json();

        return data;
    } catch (err) {
        console.log("Erro na requisição post carrinho: "+ err)
        return;
    }
}