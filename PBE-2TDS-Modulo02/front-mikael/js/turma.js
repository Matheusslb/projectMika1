// Função para carregar as turmas após o login
function carregarTurmas() {
    // Recuperando o professorId do localStorage
    const professorId = localStorage.getItem('professorId');

    if (!professorId) {
        alert('Erro: Você não está logado!');
        return;
    }

    // Realizando a requisição para carregar as turmas
    fetch('http://localhost:8080/turma')
        .then(response => {
            if (!response.ok) {
                throw new Error('Falha ao carregar as turmas');
            }
            return response.json();  // Convertendo a resposta para JSON
        })
        .then(turmas => {
            // Exibindo as turmas na tabela
            const tabelaTurmas = document.querySelector('.layoutTable');
            turmas.forEach(turma => {
                const row = tabelaTurmas.insertRow();
                row.innerHTML = `
                    <td>${turma.numero}</td>
                    <td>${turma.nome}</td>
                    <td><input class="delete" type="button" value="Excluir"> <a href="atividades.html"><input class="view" type="button" value="Visualizar"></a></td>
                `;
            });
        })
        .catch(error => {
            alert('Erro ao carregar turmas: ' + error.message);
            console.error('Erro:', error);
        });
}

// Chama a função para carregar as turmas
carregarTurmas();
