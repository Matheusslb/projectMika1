document.getElementById('loginButton').addEventListener('click', function(event) {
    event.preventDefault();  

    const email = document.getElementById('emailLogin').value;
    const senha = document.getElementById('senhaLogin').value; 
    
    const termsAccepted = document.getElementById('checkTerms').checked;
    if (!termsAccepted) {
        alert('Você precisa aceitar os termos de uso.');
        return;
    }

    const loginData = {
        email: email,
        senha: senha  
    };

    fetch('http://seu-servidor.com/api/login', { 
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginData)
    })
    .then(response => response.json()) 
    .then(data => {
        if (data.success) {
            window.location.href = '/html/principal.html';
        } else {
            alert('Erro: ' + data.message);
        }
    })
    .catch(error => {
        alert('Erro de comunicação com o servidor: ' + error.message);
    });
});
