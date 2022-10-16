import fetch from 'unfetch';
import {message} from "antd";

const checkStatus = response => {
  if (response.ok) {
    return response;
  }
  // convert non-2xx HTTP responses into errors:
  const error = new Error(response.statusText);
  error.response = response;
  return Promise.reject(error);
}

export const getAllStudents = () =>
  fetch("api/v1/students")
    .then(checkStatus);

export const saveStudent = (student) =>
  fetch("api/v1/students", {
    headers: {
      'Content-Type': 'application/json'
    },
    method: 'POST',
    body: JSON.stringify(student)
  });

export const deleteStudent = (e) => {
  message.success('delete student success');
  // message.error('delete student fail');
};