async function shortenUrl() {
    const originalUrlInput = document.getElementById('originalUrl');
    const resultContainer = document.getElementById('resultContainer');
    const shortenedUrlAnchor = document.getElementById('shortenedUrl');
    const errorMessage = document.getElementById('errorMessage');

    errorMessage.classList.add('hidden');
    resultContainer.classList.add('hidden');

    const urlValue = originalUrlInput.value;

    if (!urlValue) {
        showError("Por favor insira uma URL.");
        return;
    }

    const requestBody = {
        url: urlValue
    };

    try {
        const response = await fetch('/shorten-url', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestBody)
        });

        if (!response.ok) {
            if (response.status === 400) {
                throw new Error('URL inválida! Por favor insira uma URL válida!');
            }
            throw new Error('Erro ao encurtar a URL. Tente novamente.');
        }

        const data = await response.json();

        const fullShortUrl = data.url;

        shortenedUrlAnchor.href = fullShortUrl;
        shortenedUrlAnchor.textContent = fullShortUrl;
        resultContainer.classList.remove('hidden');

    } catch (error) {
        showError(error.message);
    }
}

function showError(message) {
    const errorMessage = document.getElementById('errorMessage');
    errorMessage.textContent = message;
    errorMessage.classList.remove('hidden');
}

function copyToClipboard() {
    const urlText = document.getElementById('shortenedUrl').textContent;
    navigator.clipboard.writeText(urlText);
    alert("URL copiada!");
}