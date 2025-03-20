document.addEventListener("DOMContentLoaded", function () {
    const table = document.querySelector("table");
    const headers = table.querySelectorAll("th");
    let sortOrder = 1; // 1 = Crescente, -1 = Decrescente

    headers.forEach((header, columnIndex) => {
        header.addEventListener("click", function () {
            const rows = Array.from(table.querySelector("tbody").rows);
            const isDateColumn = columnIndex === 2; // Índice da coluna de data

            rows.sort((rowA, rowB) => {
                let cellA = rowA.cells[columnIndex].innerText.trim();
                let cellB = rowB.cells[columnIndex].innerText.trim();

                if (isDateColumn) {
                    // Converte as datas para um formato comparável (AAAA-MM-DD)
                    cellA = new Date(cellA.split("/").reverse().join("-"));
                    cellB = new Date(cellB.split("/").reverse().join("-"));
                } else if (!isNaN(cellA) && !isNaN(cellB)) {
                    // Converte para número se possível
                    cellA = Number(cellA);
                    cellB = Number(cellB);
                }

                return cellA > cellB ? sortOrder : cellA < cellB ? -sortOrder : 0;
            });

            sortOrder *= -1; // Alterna a ordem da classificação

            // Remove e reinsere as linhas ordenadas
            const tbody = table.querySelector("tbody");
            tbody.innerHTML = "";
            rows.forEach(row => tbody.appendChild(row));
        });
    });
});



