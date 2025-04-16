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

    fetch('http://localhost:8080/professor/login', { 
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginData)
    })
    .then(response => response.json()) 
    .then(data => {
        console.log(data); 
        if (data.success) {
            // Buscar o professor pelo email e salvar o ID no localStorage
            fetch(`http://localhost:8080/professor/email/${email}`)
                .then(res => res.json())
                .then(professor => {
                    localStorage.setItem('professorId', professor.id); // salvando o ID
                    window.location.href = '/html/principal.html';
                })
                .catch(err => {
                    alert('Erro ao obter dados do professor: ' + err.message);
                });
        } else {
            alert('Erro: ' + (data.message || 'Login falhou.'));
        }
    })
    .catch(error => {
        alert('Erro de comunicação com o servidor: ' + error.message);
    });
});
