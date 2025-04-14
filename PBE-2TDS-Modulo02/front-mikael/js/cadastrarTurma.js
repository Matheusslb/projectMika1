document.getElementById("cadastrarTurma").addEventListener("click", function () {
    const nome = document.getElementById("nomeTurma").value;

    // ID do professor (aqui está fixo com ID 1, ajuste isso depois conforme login real)
    const professorId = 1;

    if (!nome.trim()) {
        alert("Digite o nome da turma!");
        return;
    }

    const turma = {
        nome: nome,
        professor: {
            id: professorId
        }
    };

    fetch("http://localhost:8080/turma", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(turma)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Erro ao cadastrar turma");
        }
        return response.json();
    })
    .then(data => {
        alert("Turma cadastrada com sucesso!");
        window.location.href = "/html/principal.html";
    })
    .catch(error => {
        alert("Erro: " + error.message);
    });
});
