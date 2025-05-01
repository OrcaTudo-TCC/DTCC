
const endpoint = "http://localhost:8080/pedido";

async function getPedido(idUsuario) {
    try {
        if(typeof idUsuario !== 'number' || !Number.isInteger(idUsuario)){
            throw new Error("O id do usuário precisa ser um Inteiro");
        }
        const response = await fetch(endpoint + "/" + idUsuario,{
            method: "GET",
            headers: { "Content-Type": "application/json" }
        })
        if(!response.ok){
            throw new Error("Erro na requisição: "+ response.status);
        }
        const data = await response.json();

        return data;
        
    } catch (error) {
        console.log("Erro no método get fornecedor: "+ error)
    }
}
async function postPedido(idCarrinho) {
    try {
        if(typeof idCarrinho !== 'number' || !Number.isInteger(idCarrinho)){
            throw new Error("O id do carrinho precisa ser um Inteiro");
        }
        
        const response = await fetch(endpoint+"/"+idCarrinho,{
            method: "POST",
            headers: { "Content-Type": "application/json" }
        })
        if(!response.ok){
            throw new Error("Erro na requisição: "+ response.status);
        }
        const data = response.json();

        return data;
    } catch (error) {
        console.log9("Erro no método post pedido: "+ error);
    }
}
async function deletePedido(idPedido) {
    try {
        if(typeof idPedido !== 'number' || !Number.isInteger(idPedido)){
            throw new Error("O id do pedido precisa ser um Inteiro");
        }
        const response = await fetch(endpoint+"/"+idPedido,{
            method: "DELETE",
            headers: { "Content-Type": "application/json" }
        })
        if(!response.ok){
            throw new Error("Erro na requisição: "+ response.status)
        }
        const data = await response.json();

        return data;
    } catch (error) {
        console.log("Erro no método delete pedido: "+ error);
    }
}