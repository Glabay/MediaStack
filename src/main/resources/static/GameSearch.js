const gameSearchForm = document.getElementById("gameSearchForm");
const gameSearchInput = document.getElementById("gameSearch");
const resultsContainer = document.getElementById("resultsContainer");
const statusMessage = document.getElementById("statusMessage");

gameSearchForm.addEventListener("submit", async function (event) {
    event.preventDefault();

    const query = gameSearchInput.value.trim();
    if (!query) {
        statusMessage.textContent = "Please enter a game title.";
        return;
    }

    statusMessage.textContent = "Searching...";
    resultsContainer.innerHTML = "";

    try {
        const response = await fetch(`/api/v1/games/query?gameSearch=${encodeURIComponent(query)}`, {
            method: "GET",
            headers: {
                "Accept": "application/json"
            }
        });

        if (!response.ok) {
            alert("Failed to fetch search results.");
        }

        const data = await response.json();
        const games = data.results ?? [];

        if (games.length === 0) {
            statusMessage.textContent = "No games found.";
            resultsContainer.innerHTML = `
                    <div class="empty-state">
                        No results matched your search.
                    </div>
                `;
            return;
        }

        statusMessage.textContent = `Found ${games.length} game(s).`;
        renderGames(games);
    } catch (error) {
        console.error(error);
        statusMessage.textContent = "Something went wrong while searching.";
        resultsContainer.innerHTML = `
                <div class="empty-state">
                    Unable to load game results right now.
                </div>
            `;
    }
});

function renderGames(games) {
    resultsContainer.innerHTML = "";

    games.forEach(game => {
        const card = document.createElement("div");
        card.className = "game-card";

        const safeName = game.name ?? "Unknown Title";
        const safeDescription = game.description ?? "No description available.";
        const safeYear = game.year ?? "Unknown";

        card.innerHTML = `
                <div class="game-image-wrapper">
                    <img class="game-image" src="${game.image}" alt="${escapeHtml(safeName)} cover image">
                </div>
                <div class="game-content">
                    <h2 class="game-title">${escapeHtml(safeName)}</h2>
                    <div class="game-meta">
                        <strong>Release Year:</strong> ${escapeHtml(String(safeYear))}
                    </div>
                    <p class="game-description">${escapeHtml(safeDescription)}</p>
                    <button class="save-button" type="button">Save Game</button>
                </div>
            `;

        const saveButton = card.querySelector(".save-button");
        saveButton.addEventListener("click", async function () {
            await saveGame(game, saveButton);
        });

        resultsContainer.appendChild(card);
    });
}

async function saveGame(game, button) {
    button.disabled = true;
    const originalText = button.textContent;
    button.textContent = "Saving...";

    try {
        const response = await fetch("/api/v1/games", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: JSON.stringify(game)
        });

        if (!response.ok) {
            alert("Failed to save game.");
        }

        button.textContent = "Saved!";
    } catch (error) {
        console.error(error);
        button.disabled = false;
        button.textContent = originalText;
        alert("Could not save this game.");
    }
}

function escapeHtml(value) {
    return String(value)
        .replaceAll("&", "&amp;")
        .replaceAll("<", "&lt;")
        .replaceAll(">", "&gt;")
        .replaceAll('"', "&quot;")
        .replaceAll("'", "&#039;");
}