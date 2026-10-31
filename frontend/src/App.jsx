import { useState } from 'react'
import ActivityManager from './components/ActivityManager.jsx'

export default function App() {
  const [page, setPage] = useState('add')
  const [menuOpen, setMenuOpen] = useState(false)
  const go = (p) => { setPage(p); setMenuOpen(false) }

  return (
    <>
      <nav className="navbar navbar-expand-md navbar-dark brand-bar">
        <div className="container">
          <span className="navbar-brand fw-bold">🤝 Volunteer Activities</span>
          <button className="navbar-toggler" type="button" aria-label="Toggle navigation" onClick={() => setMenuOpen(!menuOpen)}>
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className={`collapse navbar-collapse ${menuOpen ? 'show' : ''}`}>
            <ul className="navbar-nav ms-auto">
              <li className="nav-item"><button className={`nav-link btn btn-link ${page === 'add' ? 'active fw-bold' : ''}`} onClick={() => go('add')}>Add Activity</button></li>
              <li className="nav-item"><button className={`nav-link btn btn-link ${page === 'view' ? 'active fw-bold' : ''}`} onClick={() => go('view')}>View / Delete Activities</button></li>
            </ul>
          </div>
        </div>
      </nav>
      <div className="container py-4">
        <h2 className="mb-4">Volunteer Activity Management</h2>
        <ActivityManager page={page} />
      </div>
    </>
  )
}
