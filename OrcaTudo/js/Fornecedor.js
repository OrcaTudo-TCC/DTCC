
const endpoint = "http://localhost:8080/fornecedor"

const endpoint2 = "http://localhost:8080/auth/signFornecedor"

async function getFornecedor(id){

     if(typeof id === 'number' && Number.isInteger(id)){
        // se a requisição conter parametro numérico
        try{
            const response = await fetch(endpoint+"/"+id, { method: "GET" 

            });
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
            const response = await fetch(endpoint+"/"+id,{ method: "GET"});
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

// deve se enviar um objeto fornecedor com todos os atributos e valores
async function postFornecedor(fornecedor) {
    const camposObrigatorios = [
        'nome',
        'password',
        'razao_social',
        'email',
        'documento',
        'telefone',
        'endereco',
        'areaDeAtuacao',
        'descricao',
        'avaliacao'
      ];
    // Verifica se é um objeto
    if (typeof dados !== 'object' || dados === null) {
        throw new Error("Parâmetro inválido: não é um objeto.");
      }
    
    // Verifica se todos os campos obrigatórios estão presentes
    const todosPresentes = camposObrigatorios.every(campo => campo in dados);
    if (!todosPresentes) {
        throw new Error("Objeto incompleto. Faltando algum campo.");
    }
    try{
        const response = await fetch(endpoint2,{ 
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                "nome": fornecedor.nome,
                "password": fornecedor.password,
                "razao_social": fornecedor.razao_social,
                "email": fornecedor.email,
                "documento": fornecedor.documento,
                "telefone": fornecedor.telefone,
                "endereco": fornecedor.endereco,
                "areaDeAtuacao": fornecedor.areaDeAtuacao,
                "descricao": fornecedor.descricao,
                "avaliacao": fornecedor.avaliacao
            })
        });
        const data = response.json();
        if(!response.ok){
            throw new Error("Erro na requsição: "+ response.status)
        }
        return data;
    }catch(err){
        console.log("Erro na requisição post fornecedor: "+ err)
    }
    const data = await response.json()
    return data;

}

async function putFornecedor(id, atributo , valor){
    try{
        const response = await fetch(endpoint+ "/"+ id , {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                [atributo] : valor
            })
        });
        const data = await response.json();

        if(!response.ok){
            throw new Error("Erro na requisição put fronecedor: "+ response.status);
        }
    }catch(err){
        console.log("Erro na requsição put fornecedor: "+ err.status);
    }
}
getFornecedor()
