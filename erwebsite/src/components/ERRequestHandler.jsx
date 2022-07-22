import { useState,  useEffect } from 'react';
import axios from 'axios';
import { ERRequestPending } from './ERRequestPending';
import { ERRequestAD } from './ERRequestAD';

// this component is for handling reimbursement request
export const ERRequestHandler = () => {
    const [expreq, setExpreq] = useState([]);

    // this will get all the reimbursement request from the database and display them
    useEffect(()=>{
        axios.get('http://localhost:8080/expensereimbursement/request')
            .then(res => setExpreq(res.data));
    },[]);

    // this table displays all the pending, appoved, and denied request
    return(
        <>
            <br/>
            <table className='tables'>
                <thead className='tHeader'>
                    <tr>
                        <th className='tH'>Employee Name</th>
                        <th className='tH'>Reason</th>
                        <th className='tH'>Notes</th>
                        <th className='tH'>Request Status</th>
                    </tr>                  
                </thead>
                <tbody>                 
                    {expreq && expreq.map( er  => {
                       if (er.status.statusId === 1){
                            return <ERRequestPending key = {er.expenseId} er = { er }/>;                   
                       }
                    })}
                </tbody>
                <br/>
                <tbody>
                    {expreq && expreq.map( er  => {
                       if (er.status.statusId === 2){
                            return <ERRequestAD key = {er.expenseId} er = { er } />;                   
                       }
                    })}
                </tbody>
                <br/>
                <tbody> 
                    {expreq && expreq.map( er  => {
                       if (er.status.statusId === 3){
                            return <ERRequestAD key = {er.expenseId} er = { er } />;                   
                       }
                    })}
                </tbody>
            </table>
        </>
    );
}