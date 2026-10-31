import { useEffect, useState } from 'react'
import { API_URL } from '../config.js'

const emptyForm = { activityName: '', organizerName: '', volunteerCount: '0', active: 'true' }

export default function ActivityManager({ page }) {
  const [form, setForm] = useState(emptyForm)
  const [errors, setErrors] = useState({})
  const [activities, setActivities] = useState([])
  const [loading, setLoading] = useState(false)
  const [saving, setSaving] = useState(false)
  const [alert, setAlert] = useState(null)

  const showAlert = (type, text) => { setAlert({ type, text }); setTimeout(() => setAlert(null), 4000) }

  const loadActivities = async () => {
    setLoading(true)
    try {
      const res = await fetch(`${API_URL}/activities`)
      if (!res.ok) throw new Error('Server returned ' + res.status)
      setActivities(await res.json())
    } catch (e) { showAlert('danger', 'Could not load activities. Is the backend running? (' + e.message + ')') }
    finally { setLoading(false) }
  }

  useEffect(() => { if (page === 'view') loadActivities() }, [page])

  const validate = () => {
    const e = {}
    if (!form.activityName.trim()) e.activityName = 'Activity name is required'
    else if (form.activityName.trim().length > 120) e.activityName = 'Maximum 120 characters'
    if (!form.organizerName.trim()) e.organizerName = 'Organizer name is required'
    else if (form.organizerName.trim().length > 120) e.organizerName = 'Maximum 120 characters'
    if (form.volunteerCount === '' || !Number.isInteger(Number(form.volunteerCount)) || Number(form.volunteerCount) < 0) e.volunteerCount = 'Enter a whole number of 0 or more'
    setErrors(e)
    return Object.keys(e).length === 0
  }

  const handleChange = (ev) => setForm({ ...form, [ev.target.name]: ev.target.value })

  const handleSubmit = async (ev) => {
    ev.preventDefault()
    if (!validate()) return
    setSaving(true)
    try {
      const res = await fetch(`${API_URL}/activities`, {
        method: 'POST', headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ activityName: form.activityName.trim(), organizerName: form.organizerName.trim(), volunteerCount: Number(form.volunteerCount), active: form.active === 'true' })
      })
      const data = await res.json()
      if (!res.ok) throw new Error(data.errors ? Object.values(data.errors).join(', ') : data.message)
      showAlert('success', `Activity added successfully (ID ${data.id})`)
      setForm(emptyForm); setErrors({})
    } catch (e) { showAlert('danger', 'Failed to add activity: ' + e.message) }
    finally { setSaving(false) }
  }

  const handleDelete = async (id) => {
    if (!window.confirm(`Delete activity ${id}?`)) return
    try {
      const res = await fetch(`${API_URL}/activities/${id}`, { method: 'DELETE' })
      const data = await res.json()
      if (!res.ok) throw new Error(data.message)
      showAlert('success', data.message); loadActivities()
    } catch (e) { showAlert('danger', 'Failed to delete: ' + e.message) }
  }

  const field = (name) => `form-control ${errors[name] ? 'is-invalid' : ''}`

  return (
    <>
      {alert && <div className={`alert alert-${alert.type}`} role="alert">{alert.text}</div>}
      {page === 'add' && <div className="card p-4">
        <h4 className="mb-3">Add New Volunteer Activity</h4>
        <form onSubmit={handleSubmit} noValidate><div className="row g-3">
          <div className="col-md-6"><label className="form-label">Activity Name</label><input name="activityName" className={field('activityName')} value={form.activityName} onChange={handleChange} placeholder="e.g. Tree Plantation"/><div className="invalid-feedback">{errors.activityName}</div></div>
          <div className="col-md-6"><label className="form-label">Organizer Name</label><input name="organizerName" className={field('organizerName')} value={form.organizerName} onChange={handleChange} placeholder="e.g. Green Club"/><div className="invalid-feedback">{errors.organizerName}</div></div>
          <div className="col-md-6"><label className="form-label">Volunteer Count</label><input name="volunteerCount" type="number" min="0" step="1" className={field('volunteerCount')} value={form.volunteerCount} onChange={handleChange}/><div className="invalid-feedback">{errors.volunteerCount}</div></div>
          <div className="col-md-6"><label className="form-label">Activity Active?</label><select name="active" className="form-select" value={form.active} onChange={handleChange}><option value="true">Yes</option><option value="false">No</option></select></div>
        </div><button className="btn btn-primary mt-4" disabled={saving}>{saving ? 'Saving...' : 'Add Activity'}</button></form>
      </div>}
      {page === 'view' && <div className="card p-4">
        <div className="d-flex justify-content-between align-items-center mb-3"><h4 className="mb-0">All Volunteer Activities</h4><button className="btn btn-outline-primary btn-sm" onClick={loadActivities}>Refresh</button></div>
        {loading ? <div className="text-center py-4"><div className="spinner-border text-primary" role="status"></div><div className="mt-2">Loading activities...</div></div> : activities.length === 0 ? <div className="text-center text-muted py-4">No activities found. Add one from the “Add Activity” page.</div> :
          <div className="table-responsive"><table className="table table-hover align-middle"><thead className="table-light"><tr><th>ID</th><th>Activity Name</th><th>Organizer</th><th>Volunteers</th><th>Active</th><th>Action</th></tr></thead><tbody>
            {activities.map((a) => <tr key={a.id}><td>{a.id}</td><td>{a.activityName}</td><td>{a.organizerName}</td><td>{a.volunteerCount}</td><td><span className={`badge ${a.active ? 'bg-success' : 'bg-secondary'}`}>{a.active ? 'Active' : 'Inactive'}</span></td><td><button className="btn btn-danger btn-sm" onClick={() => handleDelete(a.id)}>Delete</button></td></tr>)}
          </tbody></table></div>}
      </div>}
    </>
  )
}
