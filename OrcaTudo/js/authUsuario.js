const endpointAuth = "http://localhost:8080/auth/login";
const endpointForn = "http://localhost:8080/auth/loginFornecedor"

async function login(email, password) {
  if (!email || !password) {
    throw new Error("Email e senha são obrigatórios.");
  }

  try {
    const response = await fetch(endpointAuth, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        email: email,
        password: password
      })
    });

    if (!response.ok) {
      if (response.status === 404) {
        console.log("Usuário não encontrado.");
        return null;
      }
      throw new Error("Erro no login: " + response.status);
    }

    const data = await response.json();
    return data;

  } catch (err) {
    console.log("Erro na requisição de login: " + err.message);
    return null;
  }
}

async function loginFornecedor(email, password) {
  if (!email || !password) {
    throw new Error("Email e senha são obrigatórios.");
  }

  try {
    const response = await fetch(endpointForn, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        email: email,
        password: password
      })
    });

    if (!response.ok) {
      if (response.status === 404) {
        console.log("Usuário não encontrado.");
        return null;
      }
      throw new Error("Erro no login: " + response.status);
    }

    const data = await response.json();
    return data;

  } catch (err) {
    console.log("Erro na requisição de login: " + err.message);
    return null;
  }
}

//Método usado para decotificar o token e tranformá-lo em JSON

function decodificarJWT(token) {
  const base64Url = token.split('.')[1];
  if (!base64Url) throw new Error('JWT inválido');

  const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
  const jsonPayload = decodeURIComponent(atob(base64).split('').map(c =>
    '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2)
  ).join(''));

  return JSON.parse(jsonPayload);
}





