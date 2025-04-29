
const endpoint = "http://localhost:8080/fornecedor"

function getFornecedor(){
    fetch(endpoint)
    .then(res=>res.json())
    .then(res=>{
        console.log(res)
    }).catch(err=>console.log("Erro na requisição: "+ err))
}

getFornecedor()