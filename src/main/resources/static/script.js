const apiBase = "http://localhost:8080/Birthday";

document.getElementById("birthdayForm").addEventListener("submit", async (e) => {
  e.preventDefault();
  const name = document.getElementById("name").value;
  const date = document.getElementById("date").value;

  await fetch(apiBase, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ name, date })
  });

  document.getElementById("birthdayForm").reset();
  loadAll();
});

async function loadAll() {
  const res = await fetch(apiBase);
  const data = await res.json();
  renderList(data);
}

async function loadToday() {
  const res = await fetch(`${apiBase}/today`);
  const data = await res.json();
  renderList(data);
}

async function loadMonth() {
  const res = await fetch(`${apiBase}/month`);
  const data = await res.json();
  renderList(data);
}

async function deleteBirthday(id) {
  await fetch(`${apiBase}/${id}`, { method: "DELETE" });
  loadAll();
}

function renderList(birthdays) {
  const list = document.getElementById("birthdayList");
  list.innerHTML = "";
  if (birthdays.length === 0) {
    list.innerHTML = `<li class="list-group-item text-center text-muted">No birthdays found.</li>`;
    return;
  }

  birthdays.forEach(b => {
    const item = document.createElement("li");
    item.className = "list-group-item d-flex justify-content-between align-items-center";
    item.innerHTML = `
      <div>
        <strong>${b.name}</strong><br>
        <small>${b.date}</small>
      </div>
      <button class="btn btn-sm btn-danger" onclick="deleteBirthday(${b.id})">Delete</button>
    `;
    list.appendChild(item);
  });
}

window.onload = loadAll;
