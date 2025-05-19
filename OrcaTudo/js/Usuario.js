const endpointUsuario = "http://localhost:8080/usuarios";
const endpointCadastroUsuario = "http://localhost:8080/auth/signupUsuario";


// Retorna o usuário buscado por ID ou todos se não houver parâmetro
async function getUsuarioById(id) {
  if (typeof id === "number" && Number.isInteger(id)) {
    const url = new URL(endpointUsuario);
    url.searchParams.append("id", id);
    try {
      const response = await fetch(url.toString(), {
        method: "GET",
        headers: { "Content-Type": "application/json" }
      });

      if (!response.ok) {
        throw new Error("Erro na requisição: " + response.status);
      }

      const data = await response.json();
      return data;
    } catch (err) {
      console.log(err);
      return null;
    }
  } else if (id === undefined) {
    try {
      const response = await fetch(endpointUsuario);
      const data = await response.json();
      return data;
    } catch (err) {
      console.log("Erro na requisição get usuario: " + err);
      return null;
    }
  } else {
    console.log("Requisição com parâmetro inválido");
    return null;
  }
}

// Retorna o usuário buscado por e-mail
async function getUsuarioByEmail(email) {
  if (typeof email === "string") {
    const url = new URL(endpointUsuario);
    url.searchParams.append("email", email);
    try {
      const response = await fetch(url.toString(), {
        method: "GET",
        headers: { "Content-Type": "application/json" }
      });

      if (!response.ok) {
        throw new Error("Erro na requisição: " + response.status);
      }

      const data = await response.json();
      return data;
    } catch (err) {
      console.log(err);
      return null;
    }
  } else {
    console.log("Email inválido");
    return null;
  }
}

// Cadastra um novo usuário
async function postUsuario(usuario) {
  const camposObrigatorios = [
    "nome",
    "password",
    "email",
    "documento",
    "telefone",
    "endereco"
  ];

  if (typeof usuario !== "object" || usuario === null) {
    throw new Error("Parâmetro inválido: não é um objeto.");
  }

  const todosPresentes = camposObrigatorios.every(campo => campo in usuario);
  if (!todosPresentes) {
    throw new Error("Corpo da requisição incompleto. Faltando algum campo.");
  }

  try {
    const response = await fetch(endpointCadastroUsuario, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(usuario)
    });

    if (!response.ok) {
      throw new Error("Erro na requisição: " + response.status);
    }

    const data = await response.json();
    return data;
  } catch (err) {
    console.log("Erro na requisição post usuario: " + err);
    return null;
  }
}

// Atualiza um atributo do usuário
async function putUsuario(id, atributo, valor) {
  if (!(typeof id === "number" && Number.isInteger(id))) {
    throw new Error("Id inválido, precisa ser um número inteiro");
  }

  try {
    const response = await fetch(`${endpointUsuario}/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        [atributo]: valor
      })
    });

    if (!response.ok) {
      throw new Error("Erro na requisição PUT: " + response.status);
    }

    const data = await response.json();
    return data;
  } catch (err) {
    console.log("Erro na requisição put usuario: " + err);
    return null;
  }
}

// Deleta um usuário
async function deleteUsuario(id) {
  if (!(typeof id === "number" && Number.isInteger(id))) {
    throw new Error("Id inválido, precisa ser um número inteiro");
  }

  try {
    const response = await fetch(`${endpointUsuario}/${id}`, {
      method: "DELETE",
      headers: { "Content-Type": "application/json" }
    });

    if (!response.ok) {
      throw new Error("Erro na requisição DELETE: " + response.status);
    }

    return true;
  } catch (err) {
    console.log("Erro na requisição delete usuario: " + err);
    return false;
  }
}
