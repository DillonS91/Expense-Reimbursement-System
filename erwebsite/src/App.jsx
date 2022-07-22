import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import { Error, Home, Request, Submissions } from './pages'
import { Navigation } from './components/Navigation';

export const App = () => {

    return(       
        <BrowserRouter>
            <Navigation>
                 <section className='nav-section'>
                     <div className='nav-item'>
                        <Link className='nav-item' to='/Submissions'>Submissions</Link>
                    </div>
                    <div className='nav-item'>
                        <Link className='nav-item' to='/Request'>Request</Link>
                    </div>
                </section>
                <section></section>
            </Navigation>
            <Routes>
                <Route path='/' element={<Home/>}/>
                <Route path='/Submissions' element={<Submissions/>}/>
                <Route path='/Request' element={<Request/>}/> 
                <Route path='*' element={<Error/>}/>
            </Routes>
        </BrowserRouter>
    );
}