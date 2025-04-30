
const endpoint = "http://localhost:8080/fornecedor"

async function getFornecedor(id){

     if(typeof id === 'number' && Number.isInteger(id)){
        // se a requisição conter parametro numérico
        try{
            const response = await fetch(endpoint, { method: "GET" });
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
            const response = await fetch(endpoint,{ method: "GET"});
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
        console.log('Parâmetro inválido: não é um objeto.');
        return;
      }
    
    // Verifica se todos os campos obrigatórios estão presentes
    const todosPresentes = camposObrigatorios.every(campo => campo in dados);
    if (!todosPresentes) {
        console.log('Objeto incompleto. Faltando algum campo.');
        return;
    }
    const response = await fetch(endpoint,{ method: "POST" });
    const data = await response.json()
    return data;

}
getFornecedor()
