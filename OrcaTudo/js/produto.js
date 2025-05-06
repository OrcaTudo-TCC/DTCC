
const endpoint = "http://localhost:8080/produtos";

/*  Para criar um produto deve-se passar por parêmtro um objeto conforme a seguir:
    {
        "nome": "string",
        "descricao": "string",
        "preco": 0.0,
        "nomeSubcategoriaFinal": "string",
        "nomeFornecedor": "string",
    }
    e o segundo parametro deve ser a imagem.
*/
async function postProduto(produto , imagem) {
    try {
        const camposObrigatorios = [
            "nome",
            "descricao",
            "preco",
            "nomeSubcategoriaFinal",
            "nomeFornecedor"
        ]
        if (typeof produto !== 'object' || produto === null) {
            throw new Error("Parâmetro inválido: não é um objeto.");
        }
        const todosPresentes = camposObrigatorios.every(campo => campo in produto);
        if (!todosPresentes) {
            throw new Error("corpo da requisição incompleto. Faltando algum campo.");
        }
        const formData = FormData();
        formData.append("nome", produto.nome);
        formData.append("descricao", descricao);
        formData.append("preco", preco);
        formData.append("nomeDasubcategoriafinal", idSubcategoriaFinal);
        formData.append("nomeDoFornecedor", idFornecedor);
        formData.append("imagem", imagem);
        //requisição com fecth
        const response = fetch(endpoint,{
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: formData
        })
        if(!response.ok){
            throw new Error("Erro na requisição: "+ response.status);
        }
        const data = (await response).json();

        return data;
    } catch (error) {
        console.log("Erro no método post item: "+ error)
    }
}

async function getProdutoById(idProduto){
    try {
        if(typeof idProduto !== 'number' || !Number.isInteger(idProduto)){
            throw new Error("O id do produto precisa ser um númeor inteiro");
        }
        const response = await fetch(endpoint+ "/"+ idProduto,{
            method: "GET",
            headers: { "Content-Type": "application/json" }
        })
        if(!response.ok){
            throw new Error("Erro na resposta da requisição: "+ response.status);
        }
        const data = response.json();

        return data;
    } catch (error) {
        console.log("Erro na requisição get produto by id: "+ error)
    }
}

async function getProdutoByCategoriaFinal(nomeCategoriaFinal){
    try {
        if(typeof nomeCategoriaFinal === "string"){
            throw new Error("O nome categoria precisa ser do tipo String");
        }
        const response = await fetch(endpoint+"/"+nomeCategoriaFinal,{
            method:"GET",
            headers: { "Content-Type": "application/json" }
        })
        if(!response.ok){
            throw new Error("Erro na requisição get produto by categoria: "+response.status);
        }
        const data = response.json();
    
        return data
    } catch (error) {
        console.log("Erro na requisição get produto by categoria final: "+ error)
    }
}

async function getProdutoByFornecedorNome(nomeFornecedor){
    try {
        if(typeof nomeCategoriaFinal === "string"){
            throw new Error("O nome categoria precisa ser do tipo String");
        }
        const response = await fetch(endpoint+"/"+nomeFornecedor,{
            method:"GET",
            headers: { "Content-Type": "application/json" }
        })
        if(!response.ok){
            throw new Error(response.status);
        }
        const data = response.json();

        return data;
    } catch (error) {
        console.log("Erro na requisição get produto by fornecedor nome: "+error)
    }
}
/*  O parametro do método deve ser um objeto com os seguintes atributos:
    {
  "id": 0,
  "nome": "string",
  "descricao": "string",
  "preco": 0,
  "imagem": [
    byte
  ],
  "nomeDasubcategoriaFinal": "string",
  "nomeDoFornecedor": "string"
}
*/
async function putProduto(produto){
    const camposObrigatorios = [
        'id',
        'nome',
        'descrição',
        'preco',
        'imagem',
        'nomeDasubcategoriaFinal',
        'nomeDoFornecedor'
    ];
    if (typeof fornecedor !== 'object' || fornecedor === null) {
        throw new Error("Parâmetro inválido: não é um objeto.");
    }
    const todosPresentes = camposObrigatorios.every(campo => campo in produto);
    if (!todosPresentes) {
        throw new Error("Corpo da requisção incompleto. Faltando algum campo.");
    }
    try {
        const response = await fetch(endpoint,{
            method:"PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(produto)
        })
        if(!response.ok){
            throw new Error(response.status);
        }
        const data = response.json();

        return data;
    } catch (error) {
        console.log("Erro na requisição put produto: "+error)
    }
}

async function deleteProduto(idProduto){
    try {
        if(typeof idProduto !== 'number' || !Number.isInteger(idProduto)){
            throw new Error("O id do produto precisa ser um númeor inteiro");
        }
        const response = await fetch(endpoint+"/"+idProduto,{
            method:"DELETE",
            headers: { "Content-Type": "application/json" }
        })
        if(!response.ok){
            throw new Error(response.status);
        }

        return true;
    } catch (error) {
        console.log("Erro na requisição delete produto: "+ error);
        return null;
    }
}