document.getElementById('cadastrarTurma').addEventListener('click', function(event) {
    event.preventDefault(); 

    const numeroTurma = document.getElementById('numTurma').value;
    const nomeTurma = document.getElementById('nomeTurma').value;
    const professorId = localStorage.getItem('professorId'); // Supondo que o professorId seja armazenado após o login

    if (!numeroTurma || !nomeTurma) {
        alert('Por favor, preencha todos os campos!');
        return;
    }

    const turmaData = {
        numero: numeroTurma,
        nome: nomeTurma,
        professor: { id: professorId } // Associando o professor
    };

    fetch('http://localhost:8080/turma', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(turmaData)
    })
    .then(response => response.json())
    .then(data => {
        if (data && data.id) {
            // Sucesso no cadastro da turma
            alert('Turma cadastrada com sucesso!');
            window.location.href = '/html/principal.html'; // Redirecionar para a página principal
        } else {
            // Se o retorno não tiver o id da turma, algo deu errado
            alert('Erro ao cadastrar turma!');
        }
    })
    .catch(error => {
        // Em caso de erro na comunicação com a API
        console.error(error);
        alert('Erro ao comunicar com o servidor!');
    });
});
