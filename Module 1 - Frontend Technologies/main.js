// JS Ex1: Log welcome message on load
// JS Ex1: Alert when page is fully loaded
window.addEventListener("load", function () {
  console.log("Welcome to the Community Portal");
  alert("Welcome to the Community Portal! Page fully loaded.");
  loadSavedPref();
  renderEvents();
});

// ─── JS Ex2: Data Types and Operators ───────────────────────
// JS Ex2: const for event name and date, let for seats
const eventName = "Baking Workshop";
const eventDate = "2025-05-18";
let seats = 20;

// JS Ex2: template literal concatenation
const eventInfo = `Event: ${eventName} | Date: ${eventDate} | Seats: ${seats}`;
console.log(eventInfo);

// JS Ex2: ++ to manage seat count on registration (decremented on register)
function decrementSeats() { seats--; console.log("Seats remaining:", seats); }
function incrementSeats() { seats++; console.log("Seats remaining:", seats); }

// ─── JS Ex3: Conditionals, Loops, Error Handling ────────────
// JS Ex3: Event list — each event has: name, date, seats, category, fee
const events = [
  { id: 1, name: "Baking Workshop",  date: "2025-05-18", seats: 20, category: "workshop", fee: 0,   location: "Community Centre" },
  { id: 2, name: "Cultural Night",   date: "2025-05-24", seats: 50, category: "cultural",  fee: 50,  location: "Town Hall" },
  { id: 3, name: "Sports Day",       date: "2025-06-01", seats: 100,category: "sports",    fee: 0,   location: "City Park" },
  { id: 4, name: "Health Camp",      date: "2025-06-08", seats: 0,  category: "health",    fee: 30,  location: "Town Hall" },  // full — 0 seats
  { id: 5, name: "Music Festival",   date: "2025-06-15", seats: 20, category: "music",     fee: 100, location: "City Park" },
  { id: 6, name: "Old Heritage Walk",date: "2024-12-01", seats: 10, category: "cultural",  fee: 0,   location: "Old Town" },   // past event
];

// JS Ex3: if-else — filter out past or full events
function isValidEvent(event) {
  const today = new Date().toISOString().split("T")[0];
  if (event.date < today) return false;   // past event
  if (event.seats <= 0) return false;     // full event
  return true;
}

// JS Ex3: forEach to render event cards
function renderEvents(filteredList) {
  const container = document.getElementById("eventCards");
  if (!container) return;
  const list = filteredList || events;
  container.innerHTML = "";

  list.forEach(function (ev) {
    // JS Ex3: if-else to skip past or full events
    if (!isValidEvent(ev)) return;

    // JS Ex6: .map() format: "Workshop on Baking" style
    const card = document.createElement("div");  // JS Ex7: createElement
    card.className = "eventCard";
    card.setAttribute("data-id", ev.id);
    card.innerHTML = `
      <h3>${ev.name}</h3>
      <p style="font-size:.85rem; color:#5a6b5b;">
        📅 ${ev.date} &nbsp;|&nbsp; 📍 ${ev.location} &nbsp;|&nbsp;
        💺 ${ev.seats} seats &nbsp;|&nbsp;
        💰 ${ev.fee === 0 ? "Free" : "₹" + ev.fee}
      </p>
      <button onclick="registerUser(${ev.id})">Register</button>
      <button onclick="cancelUser(${ev.id})" style="background:#c62828;">Cancel</button>
    `;
    container.appendChild(card);   // JS Ex7: appendChild
  });

  if (container.children.length === 0) {
    container.innerHTML = "<p style='color:#5a6b5b;'>No upcoming events found.</p>";
  }
}

// ─── JS Ex4: Functions, Scope, Closures, Higher-Order Functions ─
// JS Ex4: addEvent() — adds a new event to the array
function addEvent(name, date, seats, category, fee, location) {
  // JS Ex6: push() to add to array
  const newEvent = {
    id: events.length + 1,
    name, date, seats, category, fee: fee || 0, location: location || "TBD"
  };
  events.push(newEvent);
  renderEvents();
  console.log("Event added:", newEvent);
}

// JS Ex4: registerUser() with try-catch (JS Ex3)
function registerUser(eventId) {
  try {
    const ev = events.find(e => e.id === eventId);
    if (!ev) throw new Error("Event not found.");
    if (ev.seats <= 0) throw new Error("No seats available.");

    ev.seats--;              // JS Ex2: -- to manage seat count
    decrementSeats();
    trackCategoryRegistration(ev.category);  // JS Ex4: closure

    // JS Ex7: update UI
    const msg = document.getElementById("regMsg");
    if (msg) {
      msg.textContent = `✅ Registered for "${ev.name}". Seats left: ${ev.seats}`;
      msg.style.color = "#285c30";
    }
    renderEvents();
  } catch (err) {
    // JS Ex3: try-catch handles errors
    const msg = document.getElementById("regMsg");
    if (msg) { msg.textContent = "❌ " + err.message; msg.style.color = "red"; }
    console.error("Registration error:", err.message);
  }
}

// JS Ex7: cancelUser — update UI when user cancels
function cancelUser(eventId) {
  const ev = events.find(e => e.id === eventId);
  if (!ev) return;
  ev.seats++;
  incrementSeats();
  const msg = document.getElementById("regMsg");
  if (msg) {
    msg.textContent = `↩️ Cancelled registration for "${ev.name}". Seats now: ${ev.seats}`;
    msg.style.color = "#e07b39";
  }
  renderEvents();
}

// JS Ex4: filterEventsByCategory() — uses callback (higher-order function)
function filterEventsByCategory(category, callback) {
  const result = events.filter(ev => ev.category === category && isValidEvent(ev));
  if (typeof callback === "function") callback(result);
  return result;
}

// JS Ex4: Closure — track total registrations per category
function makeCategoryTracker() {
  const counts = {};  // enclosed variable
  return function (category) {
    counts[category] = (counts[category] || 0) + 1;
    console.log(`Category registrations:`, counts);
    return counts[category];
  };
}
const trackCategoryRegistration = makeCategoryTracker();

// ─── JS Ex5: Objects and Prototypes ─────────────────────────
// JS Ex5: Event constructor
function EventObj(name, date, seats, category, fee) {
  this.name = name;
  this.date = date;
  this.seats = seats;
  this.category = category;
  this.fee = fee;
}

// JS Ex5: checkAvailability on prototype
EventObj.prototype.checkAvailability = function () {
  const today = new Date().toISOString().split("T")[0];
  if (this.date < today) return "Past event";
  if (this.seats <= 0)   return "Fully booked";
  return `${this.seats} seats available`;
};

const sampleEvent = new EventObj("Baking Workshop", "2025-05-18", 20, "workshop", 0);
// JS Ex5: Object.entries to list keys and values
console.log("Event object entries:");
Object.entries(sampleEvent).forEach(([key, val]) => console.log(`  ${key}: ${val}`));
console.log("Availability:", sampleEvent.checkAvailability());

// ─── JS Ex6: Arrays and Methods ─────────────────────────────
// JS Ex6: filter — show only music events
const musicEvents = events.filter(ev => ev.category === "music");
console.log("Music events:", musicEvents);

// JS Ex6: map — format display cards e.g. "Workshop on Baking"
const formattedCards = events.map(ev =>
  `${ev.category.charAt(0).toUpperCase() + ev.category.slice(1)} on ${ev.name}`
);
console.log("Formatted event cards:", formattedCards);

// ─── JS Ex7: DOM Manipulation ───────────────────────────────
// renderEvents() above uses querySelector, createElement, appendChild, updates UI
// JS Ex7: querySelector
function updateRegMsg(text, color) {
  const el = document.querySelector("#regMsg");
  if (el) { el.textContent = text; el.style.color = color || "#285c30"; }
}

// ─── JS Ex8: Event Handling ─────────────────────────────────
// JS Ex8: onchange — filter events by category (wired in HTML via onchange)
function handleCategoryFilter(select) {
  const val = select.value;
  if (!val) { renderEvents(); return; }
  // JS Ex4: pass callback to filter function
  filterEventsByCategory(val, function (results) { renderEvents(results); });
}

// JS Ex8: keydown — quick search by event name
document.addEventListener("keydown", function (e) {
  const searchBox = document.getElementById("eventSearch");
  if (!searchBox || document.activeElement !== searchBox) return;
  // filtering happens on input event below
});

function handleSearchInput(input) {
  const query = input.value.toLowerCase();
  const filtered = events.filter(ev =>
    ev.name.toLowerCase().includes(query) && isValidEvent(ev)
  );
  renderEvents(filtered);
}

// ─── JS Ex9: Async JS, Promises, Async/Await ────────────────
// JS Ex9: Fetch events from a mock JSON endpoint using .then()/.catch()
function fetchEventsWithPromise() {
  showSpinner(true);
  fetch("https://jsonplaceholder.typicode.com/posts?_limit=3")
    .then(response => {
      if (!response.ok) throw new Error("Network response was not ok");
      return response.json();
    })
    .then(data => {
      showSpinner(false);
      const asyncMsg = document.getElementById("asyncMsg");
      if (asyncMsg) {
        asyncMsg.textContent =
          `✅ Fetched ${data.length} mock records via .then()/.catch(). (Mock API — see console)`;
        asyncMsg.style.color = "#285c30";
      }
      console.log("Fetched data (promise):", data);
    })
    .catch(err => {
      showSpinner(false);
      console.error("Fetch error:", err);
      const asyncMsg = document.getElementById("asyncMsg");
      if (asyncMsg) { asyncMsg.textContent = "❌ Fetch failed: " + err.message; asyncMsg.style.color = "red"; }
    });
}

// JS Ex9: async/await version
async function fetchEventsAsync() {
  showSpinner(true);
  try {
    const response = await fetch("https://jsonplaceholder.typicode.com/posts?_limit=3");
    if (!response.ok) throw new Error("Network error");
    const data = await response.json();
    showSpinner(false);
    const asyncMsg = document.getElementById("asyncMsg");
    if (asyncMsg) {
      asyncMsg.textContent =
        `✅ Fetched ${data.length} mock records via async/await. (Mock API — see console)`;
      asyncMsg.style.color = "#285c30";
    }
    console.log("Fetched data (async/await):", data);
  } catch (err) {
    showSpinner(false);
    console.error("Async fetch error:", err);
    const asyncMsg = document.getElementById("asyncMsg");
    if (asyncMsg) { asyncMsg.textContent = "❌ " + err.message; asyncMsg.style.color = "red"; }
  }
}

// JS Ex9: show/hide loading spinner
function showSpinner(show) {
  const spinner = document.getElementById("loadingSpinner");
  if (spinner) spinner.style.display = show ? "inline-block" : "none";
}

// ─── JS Ex10: Modern JavaScript Features ────────────────────
// JS Ex10: let, const, default parameters
function formatEvent(name, fee = 0, category = "general") {
  return `${category.toUpperCase()}: ${name} (${fee === 0 ? "Free" : "₹" + fee})`;
}
console.log("ES6 default params:", formatEvent("Baking Workshop"));

// JS Ex10: destructuring to extract event details
const [firstEvent] = events;
const { name: firstName, date: firstDate, fee: firstFee = 0 } = firstEvent;
console.log("Destructured:", firstName, firstDate, firstFee);

// JS Ex10: spread operator to clone event list before filtering
const clonedEvents = [...events];
const musicOnly = clonedEvents.filter(ev => ev.category === "music");
console.log("Music events (spread clone):", musicOnly);

// ─── JS Ex11: Working with Forms ────────────────────────────
// JS Ex11: handleFormSubmit — captures inputs, prevents default, validates
function handleFormSubmit(e) {
  e.preventDefault();  // JS Ex11: prevent default form behavior

  // JS Ex11: capture values using form.elements
  const form   = e.target;
  const name   = form.elements["name"].value.trim();
  const email  = form.elements["email"].value.trim();
  const event  = form.elements["event"].value;
  const output = document.getElementById("formOutput");

  // JS Ex11: validate inputs and show errors inline
  if (!name) { showInlineError("nameError", "Name is required."); return; }
  if (!email || !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
    showInlineError("emailError", "Enter a valid email address."); return;
  }
  if (!event) { showInlineError("eventError", "Please select an event."); return; }

  clearInlineErrors();
  if (output) {
    output.textContent = `✅ Thank you, ${name}! You're registered for: ${event}`;
    output.style.color = "#285c30";
  }

  // JS Ex12: POST to mock API
  postRegistration({ name, email, event });
}

function showInlineError(id, msg) {
  const el = document.getElementById(id);
  if (el) { el.textContent = msg; el.style.display = "block"; }
}
function clearInlineErrors() {
  ["nameError","emailError","eventError"].forEach(id => {
    const el = document.getElementById(id);
    if (el) { el.textContent = ""; el.style.display = "none"; }
  });
}

// ─── JS Ex12: AJAX & Fetch API ──────────────────────────────
// JS Ex12: POST user data to a mock API
function postRegistration(userData) {
  const statusEl = document.getElementById("submitStatus");
  if (statusEl) { statusEl.textContent = "Submitting…"; statusEl.style.color = "#5a6b5b"; }

  fetch("https://jsonplaceholder.typicode.com/posts", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(userData)
  })
    .then(res => res.json())
    .then(data => {
      // JS Ex12: setTimeout to simulate delayed response
      setTimeout(() => {
        if (statusEl) {
          statusEl.textContent = "✅ Registration sent! (Mock API response id: " + data.id + ")";
          statusEl.style.color = "#285c30";
        }
        console.log("POST response:", data);
      }, 1000);
    })
    .catch(err => {
      if (statusEl) { statusEl.textContent = "❌ Submission failed."; statusEl.style.color = "red"; }
      console.error("POST error:", err);
    });
}

// ─── JS Ex13: Debugging ─────────────────────────────────────
// JS Ex13: Log form submission steps for DevTools inspection
// Open Chrome DevTools → Console tab to see these logs
// Use Sources tab → set breakpoints on handleFormSubmit above
console.log("JS Ex13: Open DevTools (F12) → Console to see logs.");
console.log("JS Ex13: Use Sources tab to add breakpoints to handleFormSubmit().");
console.log("JS Ex13: Network tab shows fetch() POST request payload after form submit.");

// ─── JS Ex14: jQuery ────────────────────────────────────────
// JS Ex14: jQuery tasks are handled in the HTML page using the jQuery CDN
// $('#registerBtn').click(...) — handled inline in index.html
// .fadeIn() / .fadeOut() — used on event cards in index.html
// Note: React/Vue would replace manual DOM manipulation with reactive component trees,
// making large apps easier to manage and test.

// ─── HTML Ex handlers wired from index.html ─────────────────

// HTML Ex6: onblur phone validation
function validatePhone(input) {
  const err = document.getElementById("phoneError");
  if (input.value && !/^\d{10}$/.test(input.value.trim())) {
    input.style.borderColor = "red";
    if (err) err.style.display = "block";
  } else {
    input.style.borderColor = "#c0d6c2";
    if (err) err.style.display = "none";
  }
}

// HTML Ex6: onchange — show event fee
function showEventFee(select) {
  const opt = select.options[select.selectedIndex];
  const fee = opt.dataset.fee;
  const el  = document.getElementById("feeDisplay");
  if (!select.value) { if (el) el.textContent = ""; return; }
  if (el) el.textContent = fee === "0" ? "🎉 This event is free!" : `Entry fee: ₹${fee}`;
}

// HTML Ex6: keyup character counter
function countChars(textarea, counterId) {
  const el = document.getElementById(counterId);
  if (el) el.textContent = `${textarea.value.length} / 300 characters`;
}

// HTML Ex6: ondblclick — toggle image enlarge
function toggleEnlarge(img) {
  img.classList.toggle("enlarged");
  img.title = img.classList.contains("enlarged")
    ? "Double-click to shrink"
    : "Double-click to enlarge";
}

// HTML Ex7: oncanplay — video ready
function videoReady() {
  const el = document.getElementById("videoStatus");
  if (el) el.textContent = "✅ Video is ready to play!";
}

// HTML Ex7: warn before leaving if form has content
window.onbeforeunload = function () {
  const msg = document.getElementById("regMessage");
  if (msg && msg.value.trim().length > 0) {
    return "You have an unfinished registration. Leave anyway?";
  }
};

// HTML Ex8: save preference to localStorage and sessionStorage
function savePref() {
  const val = document.getElementById("prefSelect").value;
  if (!val) { document.getElementById("prefStatus").textContent = "Please select a preference."; return; }
  localStorage.setItem("preferredEvent", val);
  sessionStorage.setItem("sessionPref", val);
  document.getElementById("prefStatus").textContent = `✅ Saved "${val}" to localStorage.`;
}

// HTML Ex8: clear both storages
function clearPrefs() {
  localStorage.removeItem("preferredEvent");
  sessionStorage.removeItem("sessionPref");
  document.getElementById("prefSelect").value = "";
  document.getElementById("prefStatus").textContent = "Preferences cleared.";
}

// HTML Ex8: load saved preference on page load
function loadSavedPref() {
  const saved = localStorage.getItem("preferredEvent");
  if (saved) {
    const el = document.getElementById("prefSelect");
    if (el) el.value = saved;
    const status = document.getElementById("prefStatus");
    if (status) status.textContent = `Loaded saved preference: "${saved}"`;
  }
}

// HTML Ex9: geolocation — getCurrentPosition with error handling and high accuracy
function findNearby() {
  const result = document.getElementById("geoResult");
  if (!navigator.geolocation) {
    if (result) result.textContent = "❌ Geolocation not supported by this browser.";
    return;
  }
  if (result) result.textContent = "🔍 Detecting your location…";
  navigator.geolocation.getCurrentPosition(
    function (pos) {
      if (result) result.innerHTML =
        `📍 Lat: <strong>${pos.coords.latitude.toFixed(4)}</strong>, ` +
        `Lng: <strong>${pos.coords.longitude.toFixed(4)}</strong> ` +
        `(±${Math.round(pos.coords.accuracy)}m)`;
    },
    function (err) {
      // HTML Ex9: handle permission denial and timeout
      if (!result) return;
      if (err.code === err.PERMISSION_DENIED)
        result.textContent = "❌ Location access denied by user.";
      else if (err.code === err.TIMEOUT)
        result.textContent = "❌ Location request timed out.";
      else
        result.textContent = "❌ Could not retrieve location.";
    },
    // HTML Ex9: high accuracy options
    { enableHighAccuracy: true, timeout: 8000, maximumAge: 0 }
  );
}