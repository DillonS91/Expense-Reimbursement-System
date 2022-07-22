import axios from 'axios';
import { useRef } from 'react';

// this component will allow users to submit reuest for reimbursement
export const SubmissionTable = () =>{
    const nameRef = useRef();
    const reasonRef = useRef();
    const noteRef = useRef();
    
    // once the user has entered thier data and submitted this will post that data to the server
    const handleSubmit = async (event) => {
        try {
            event.preventDefault();
            await axios.post('http://localhost:8080/expensereimbursement/request',
                {
                    employeeName: nameRef.current.value,
                    expenseReason: reasonRef.current.value,
                    expenseNotes: noteRef.current.value
                }
            );           
            nameRef.current.value = '';
            reasonRef.current.value = '';
            noteRef.current.value = '';
        } catch (err) {
            console.error(err);
        }
    }

    return(
            <form onSubmit={handleSubmit}>
                <div>
                    <h4>Expense Reimbursement Request Submission</h4>
                    <input name = 'employeeName' ref = {nameRef} placeholder='Enter Name'/>
                    <br/>
                    <input name = 'expenseReason' ref = {reasonRef} placeholder='Enter Reason'/>
                    <br/>
                    <input name = 'expenseNotes' ref = {noteRef} placeholder='Enter Notes'/>
                    <br/>
                    <button>Submit</button>
                </div>
            </form>
    );
}