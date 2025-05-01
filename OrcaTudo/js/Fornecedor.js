
const endpoint = "http://localhost:8080/fornecedor"

const endpoint2 = "http://localhost:8080/auth/signupFornecedor"

//retorna o fornecedor buscado
async function getFornecedorById(id){

     if(typeof id === 'number' && Number.isInteger(id)){
        // se a requisição conter parametro numérico
        const url = new URL(endpoint);
        url.searchParams.append('id', id)
        try{
            const response = await fetch(url.toString(), {
                method: "GET", 
                headers: { "Content-Type": "application/json" }
            });
            if(!response.ok){
                throw new Error("Erro na requisição: "+ response.status);
            }
            const data = await response.json();

            return data;
        }catch(err){
            console.log(err);
            return null;
        }
    }
    // se a requisição não conter nennhum parametro
    else if(id === undefined){
        try{
            const response = await fetch(endpoint);
            const data = await response.json();
    
            return data
        }catch(err){
            console.log("Erro na requisição get fornecedor: "+ err)
            return null
        }
    }else{
        console.log("Requisição com parâmetro inválido");
        return null;
    }

    
}
//retorna o fornecedor buscado
async function getFornecedorByEmail(email) {
    if(typeof email === 'string'){
        // se a requisição conter parametro numérico
        const url = new URL(endpoint);
        url.searchParams.append('email', email)
        console.log(email);
        try{
            const response = await fetch(url.toString(), {
                method: "GET" ,
                headers: { "Content-Type": "application/json" }
            });
            if(!response.ok){
                throw new Error("Erro na requisição: "+ response.status);
            }
            const data = await response.json();

            return data;
        }catch(err){
            console.log(err);
            return null;
        }
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
    if (typeof fornecedor !== 'object' || fornecedor === null) {
        throw new Error("Parâmetro inválido: não é um objeto.");
      }
    
    // Verifica se todos os campos obrigatórios estão presentes
    const todosPresentes = camposObrigatorios.every(campo => campo in fornecedor);
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
        if(!response.ok){
            throw new Error("Erro na requsição: "+ response.status)
        }
        const data = await response.json();
        return data;
    }catch(err){
        console.log("Erro na requisição post fornecedor: "+ err)
    }
}
// atualiza um atributo, retorna o forncedor atualziaod
async function putFornecedor(id, atributo , valor){
    if(!(typeof id === 'number' && Number.isInteger(id))){
        throw new Error("Id inválido, id precisa ser um número inteiro")
    }
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
        return data;
    }catch(err){
        console.log("Erro na requsição put fornecedor: "+ err);
    }
}
//deleta o forncedor, retorna um boolean
async function deleteFornecedor(id) {
    if(!(typeof id === 'number' && Number.isInteger(id))){
        throw new Error("Id inválido, id precisa ser um número inteiro")
    }
    try{
        const response = await fetch(endpoint + "/" + id, {
            method: "DELETE",
            headers: { "Content-Type": "application/json" }
        });
        if(!response.ok){
            throw new Error("Erro na requisição: "+ response.status);
        }


        return true;

    }catch(err){
        console.log("Erro na requisição delete fornecedor: "+ err)
    }
}
