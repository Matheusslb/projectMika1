document.addEventListener('DOMContentLoaded', () => {
    const btnCadastrar = document.getElementById('loginButton');

    btnCadastrar.addEventListener('click', () => {
        const numero = document.getElementById('numAtv').value;
        const descricao = document.getElementById('descAtv').value;
        const professorId = localStorage.getItem('professorId');

        if (!numero || !descricao || !professorId) {
            alert("Preencha todos os campos.");
            return;
        }

        const atividade = {
            numero,
            descricao,
            professor: {
                id: professorId
            }
        };

        fetch("http://localhost:8080/atividades", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(atividade)
        })
        .then(res => {
            if (!res.ok) throw new Error("Erro ao cadastrar atividade.");
            return res.json();
        })
        .then(() => {
            alert("Atividade cadastrada com sucesso!");
            window.location.href = "atividades.html";
        })
        .catch(error => {
            console.error(error);
            alert("Erro ao cadastrar atividade.");
        });
    });
});
