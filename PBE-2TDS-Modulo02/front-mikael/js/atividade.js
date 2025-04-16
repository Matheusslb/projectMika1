document.addEventListener('DOMContentLoaded', () => {
    const professorId = localStorage.getItem('professorId');

    fetch('http://localhost:8080/atividades')
        .then(res => res.json())
        .then(atividades => {
            const tabela = document.querySelector(".layoutTable");

            atividades
                .filter(atv => atv.professor.id == professorId)
                .forEach(atv => {
                    const linha = document.createElement("tr");

                    linha.innerHTML = `
                        <td>${atv.numero}</td>
                        <td>${atv.descricao}</td>
                        <td>
                            <input class="delete" type="button" value="Excluir">
                            <a href="principal.html"><input class="view" type="button" value="Vizualizar"></a>
                        </td>
                    `;

                    tabela.appendChild(linha);
                });
        })
        .catch(err => {
            console.error(err);
            alert("Erro ao carregar atividades!");
        });
});
