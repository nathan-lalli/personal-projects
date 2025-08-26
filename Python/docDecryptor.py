import requests, re

def render_google_doc_grid(url: str):
    response = requests.get(url)
    response.raise_for_status()
    html = response.text
    text = re.sub(r"<[^>]+>", "\n", html)
    tokens = [t.strip() for t in text.splitlines() if t.strip()]

    entries = []
    i = 0
    while i < len(tokens) - 2:
        try:
            x = int(tokens[i])
            char = tokens[i + 1]
            y = int(tokens[i + 2])
            entries.append((x, y, char))
            i += 3
        except ValueError:
            i += 1

    if not entries:
        print("No character data found in document.")
        return

    max_x = max(x for x, y, c in entries)
    max_y = max(y for x, y, c in entries)

    grid = [[" " for _ in range(max_x + 1)] for _ in range(max_y + 1)]

    for x, y, c in entries:
        grid[y][x] = c

    for row in grid:
        print("".join(row))

if __name__ == "__main__":
    url = input("Enter the Google Doc URL: ").strip()
    if url:
        render_google_doc_grid(url)
    else:
        print("No URL provided.")