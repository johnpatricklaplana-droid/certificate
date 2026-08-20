import { Routes, Route } from 'react-router-dom'
import SchoolRegistrationRequests from './pages/SchoolRegistrationRequest'
import Sidebar from './components/Sidebar'

function App() {
  return (
    <div className='flex'>
      <Sidebar />

      <main
        className='flex min-h-screen bg-gray-50 w-screen justify-center py-8'
      >
        <Routes>
          <Route path="/admin/schools" element={<SchoolRegistrationRequests />} />
        </Routes>
      </main>
    </div>
  )
}

export default App