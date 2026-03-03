import { useState, useEffect } from 'react'
import { Routes, Route } from 'react-router'
import Navbar from './Navbar'
import Home from './Home'
import Book from './Book'
import BookForm from './BookForm'
import Magazine from './Magazine'
import MagazineForm from './MagazineForm'
import DiscMag from './DiscMag'
import DiscMagForm from './DiscMagForm'
import Ticket from './Ticket'
import TicketForm from './TicketForm'
import HandheldConsole from './HandheldConsole'
import HandheldConsoleForm from './HandheldConsoleForm'
import HomeConsole from './HomeConsole'
import HomeConsoleForm from './HomeConsoleForm'
import './App.css'

// Safe JSON parse — returns null instead of throwing if the response is HTML/empty
async function safeJson(res) {
    const text = await res.text();
    try { return JSON.parse(text); } catch { return null; }
}

function makeHandlers(list, setList, apiPath) {
    const handleAdd = (item) => setList(prev => [...prev, item]);

    const handleDelete = (id) => {
        if (!window.confirm('Delete this item?')) return;
        fetch(`${apiPath}/${id}`, { method: 'DELETE' })
            .then(res => { if (res.ok) setList(prev => prev.filter(x => x.id !== id)); });
    };

    const handleUpdate = (id, data) => {
        fetch(`${apiPath}/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data),
        })
            .then(async res => {
                const saved = await safeJson(res);
                if (res.ok && saved) {
                    setList(prev => prev.map(x => (x.id === id ? saved : x)));
                } else {
                    alert(`Update failed (${res.status}): ${saved?.message ?? res.statusText}`);
                }
            });
    };

    return { handleAdd, handleDelete, handleUpdate };
}

function App() {
    const [books, setBooks] = useState([]);
    const [magazines, setMagazines] = useState([]);
    const [discMags, setDiscMags] = useState([]);
    const [tickets, setTickets] = useState([]);
    const [handheldConsoles, setHandheldConsoles] = useState([]);
    const [homeConsoles, setHomeConsoles] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        Promise.all([
            fetch('/api/rest/books').then(r => r.json()),
            fetch('/api/rest/magazines').then(r => r.json()),
            fetch('/api/rest/discmags').then(r => r.json()),
            fetch('/api/rest/tickets').then(r => r.json()),
            fetch('/api/rest/handheld-consoles').then(r => r.json()),
            fetch('/api/rest/home-consoles').then(r => r.json()),
        ]).then(([b, m, d, t, h, hc]) => {
            setBooks(b); setMagazines(m); setDiscMags(d);
            setTickets(t); setHandheldConsoles(h); setHomeConsoles(hc);
            setLoading(false);
        });
    }, []);

    const bookH = makeHandlers(books, setBooks, '/api/rest/books');
    const magH = makeHandlers(magazines, setMagazines, '/api/rest/magazines');
    const discMagH = makeHandlers(discMags, setDiscMags, '/api/rest/discmags');
    const ticketH = makeHandlers(tickets, setTickets, '/api/rest/tickets');
    const handheldH = makeHandlers(handheldConsoles, setHandheldConsoles, '/api/rest/handheld-consoles');
    const homeConsoleH = makeHandlers(homeConsoles, setHomeConsoles, '/api/rest/home-consoles');

    if (loading) return (
        <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '60vh', fontSize: '1.2rem', color: '#666' }}>
            Loading inventory...
        </div>
    );

    return (
        <div style={{ maxWidth: '960px', margin: '0 auto', padding: '20px' }}>
            <Navbar />
            <Routes>
                <Route path="/" element={<Home />} />

                {/* Books */}
                <Route path="/inventory" element={
                    <div>
                        <h1>📚 Book Inventory</h1>
                        {books.length === 0 ? <p>No books found.</p> : books.map(b =>
                            <Book key={b.id} {...b} onDelete={bookH.handleDelete} onUpdate={bookH.handleUpdate} />
                        )}
                    </div>
                } />
                <Route path="/add" element={<><h1>Add Book</h1><BookForm onBookAdded={bookH.handleAdd} /></>} />

                {/* Magazines */}
                <Route path="/magazines" element={
                    <div>
                        <h1>📰 Magazine Inventory</h1>
                        {magazines.length === 0 ? <p>No magazines found.</p> : magazines.map(m =>
                            <Magazine key={m.id} {...m} onDelete={magH.handleDelete} onUpdate={magH.handleUpdate} />
                        )}
                    </div>
                } />
                <Route path="/add-magazine" element={<><h1>Add Magazine</h1><MagazineForm onMagazineAdded={magH.handleAdd} /></>} />

                {/* Disc Magazines */}
                <Route path="/discmags" element={
                    <div>
                        <h1>💿 Disc Magazine Inventory</h1>
                        {discMags.length === 0 ? <p>No disc magazines found.</p> : discMags.map(d =>
                            <DiscMag key={d.id} {...d} onDelete={discMagH.handleDelete} onUpdate={discMagH.handleUpdate} />
                        )}
                    </div>
                } />
                <Route path="/add-discmag" element={<><h1>Add Disc Magazine</h1><DiscMagForm onDiscMagAdded={discMagH.handleAdd} /></>} />

                {/* Tickets */}
                <Route path="/tickets" element={
                    <div>
                        <h1>🎟️ Ticket Inventory</h1>
                        {tickets.length === 0 ? <p>No tickets found.</p> : tickets.map(t =>
                            <Ticket key={t.id} {...t} onDelete={ticketH.handleDelete} onUpdate={ticketH.handleUpdate} />
                        )}
                    </div>
                } />
                <Route path="/add-ticket" element={<><h1>Add Ticket</h1><TicketForm onTicketAdded={ticketH.handleAdd} /></>} />

                {/* Handheld Consoles */}
                <Route path="/handheld-consoles" element={
                    <div>
                        <h1>🎮 Handheld Console Inventory</h1>
                        {handheldConsoles.length === 0 ? <p>No handheld consoles found.</p> : handheldConsoles.map(h =>
                            <HandheldConsole key={h.id} {...h} onDelete={handheldH.handleDelete} onUpdate={handheldH.handleUpdate} />
                        )}
                    </div>
                } />
                <Route path="/add-handheld" element={<><h1>Add Handheld Console</h1><HandheldConsoleForm onAdded={handheldH.handleAdd} /></>} />

                {/* Home Consoles */}
                <Route path="/home-consoles" element={
                    <div>
                        <h1>🖥️ Home Console Inventory</h1>
                        {homeConsoles.length === 0 ? <p>No home consoles found.</p> : homeConsoles.map(h =>
                            <HomeConsole key={h.id} {...h} onDelete={homeConsoleH.handleDelete} onUpdate={homeConsoleH.handleUpdate} />
                        )}
                    </div>
                } />
                <Route path="/add-home-console" element={<><h1>Add Home Console</h1><HomeConsoleForm onAdded={homeConsoleH.handleAdd} /></>} />
            </Routes>
        </div>
    );
}

export default App