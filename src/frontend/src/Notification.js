import {notification} from "antd";
import React from 'react'

const openNotificationWithIcon = (type, message, description) => {
  notification[type]({
    message: message,
    description: description,
  });
};

export const successNotificationWithIcon = (message, description) => {
  openNotificationWithIcon('success', message, description);
}

export const infoNotificationWithIcon = (message, description) => {
  openNotificationWithIcon('info', message, description);
}

export const warningNotificationWithIcon = (message, description) => {
  openNotificationWithIcon('warning', message, description);
}

export const errorNotificationWithIcon = (message, description) => {
  openNotificationWithIcon('error', message, description);
}
