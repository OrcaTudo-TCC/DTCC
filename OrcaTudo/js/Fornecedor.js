
const endpoint = "http://localhost:8080/fornecedor"

 async function getFornecedor(id){

     if(typeof id === 'number' && Number.isInteger(id)){
        // se a requisição conter parametro numérico
        try{
            const response = await fetch(endpoint, { method: "POST" });
            const data = await response.json();

            if(!response.ok){
                throw new Error("Erro na requisição: "+ response.status);
            }
            return data;
        }catch(err){
            console.log(err);
            return data;
        }
    }else if(typeof id === 'string'){
        // se a requisição conter uma String
        try{
            const response = await fetch(endpoint,{ method: "POST"});
            const data = await response.json();

            if(!response.ok){
                throw new Error("Erro na requisição: "+ response.status);
            }
            return data;
        }catch(err){
            console.log(err);
            return null;
        }
    // se a requisição não conter nennhum parametro
    }else if(typeof id === undefined){
        try{
            const response = await fetch(endpoint);
            const data = await response.json();
    
            console.log(data);
            return data
        }catch(err){
            console.log(err)
            return null
        }
    }else{
        console.log("Requisição com parâmetro inválido");
        return null;
    }

    
}
getFornecedor()
