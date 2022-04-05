import React, { Component, useEffect, useState } from 'react';
import { Button, ButtonGroup, Container, Table, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import { Link } from 'react-router-dom';
import useUser from './useUser';
import { render } from '@testing-library/react';
import AlertModal from './AlertModal';
import './css/Vote.css';

function QuestionList({ user, setUser }) {
  const [questions, setQuestions] = useState([]);
  const [successModal, setSuccessModal] = useState(false);
  const [failureModal, setFailureModal] = useState(false);

  useEffect(() => {
    async function fetchData() {
      var response = await fetch('/questions/getUserUnanswered?' + new URLSearchParams({userId: user.id}))
      var data = await response.json();
      var newQuestions = data.map((e) => e);  
      setQuestions(newQuestions);   
    }
    fetchData();
  }, []);

  async function questionVote(id, voteValue) {
    var response = await fetch('/vote/add', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        "question": id,
        "user": user.id,
        "vote": voteValue
      })
    })
    if (response.status == 201) 
    {
      setSuccessModal(true)
      var newQuestions = [...questions].filter(i => i.id !== id);
      setQuestions(newQuestions)      
    }
    else
    {
      setFailureModal(true)
    }
  }

  let qmap = questions.map((question, index) => {
    return(
      <tr key={index}>
        <td style={{wordWrap: 'break-word'}}>{question.question}</td>
          <td>
            <ButtonGroup>
              <Button size="sm" color="success" onClick={() => questionVote(question.id, 1)}>Yes</Button>
              <Button size="sm" color="danger" onClick={() => questionVote(question.id, 0)}>No</Button>
            </ButtonGroup>
          </td>
      </tr>
    )
  })

  if (qmap.length == 0) {
    qmap = <tr key={0}>
    <td>{"No unanswered questions"}</td>
    </tr>
  }


  return (
    <div className="vote-wrapper">
      <Container fluid>
        <h3>Your Unanswered Questions</h3>
        <Table className="mt-4">
          <thead>
            <tr>
              <th width="50%">Question</th>
              <th width="50%">Vote</th>
            </tr>
          </thead>
          <tbody>
            {qmap}
          </tbody>
        </Table>
        <Modal isOpen={successModal}>
          <ModalHeader>Vote Successful</ModalHeader>
          <ModalBody>
              Your vote was successfully cast
          </ModalBody>
          <ModalFooter>
              <Button color="primary" onClick={() => setSuccessModal(false)}>Continue</Button>
          </ModalFooter>
        </Modal>
        <Modal isOpen={failureModal}>
          <ModalHeader>Vote failure</ModalHeader>
          <ModalBody>
              There was an error with your vote!
          </ModalBody>
          <ModalFooter>
              <Button color="primary" onClick={() => setFailureModal(false)}>Continue</Button>
          </ModalFooter>
        </Modal>
      </Container>
    </div>
  )

}
export default QuestionList;