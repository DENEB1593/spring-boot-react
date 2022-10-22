import {notification} from "antd";
import React from 'react'

const openNotificationWithIcon = (type, message, description, placement) => {
  placement = placement || 'topRight';
  notification[type]({
    message: message,
    description: description,
    placement: placement
  });
};

export const successNotificationWithIcon = (message, description, placement) => {
  openNotificationWithIcon('success', message, description, placement);
}

export const infoNotificationWithIcon = (message, description, placement) => {
  openNotificationWithIcon('info', message, description, placement);
}

export const warningNotificationWithIcon = (message, description, placement) => {
  openNotificationWithIcon('warning', message, description, placement);
}

export const errorNotificationWithIcon = (message, description, placement) => {
  openNotificationWithIcon('error', message, description, placement);
}
