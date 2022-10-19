import React from 'react';
import { useState, useEffect } from "react";
import {deleteStudent, getAllStudents} from "./client";
import {Layout, Menu, Breadcrumb, Table, Spin, Empty, Button, Badge, Tag, Avatar, Popconfirm} from 'antd';
import {
	DesktopOutlined,
	PieChartOutlined,
	FileOutlined,
	TeamOutlined,
	UserOutlined,
	LoadingOutlined, PlusOutlined,
} from '@ant-design/icons';

import './App.css';
import StudentDrawerForm from "./StudentDrawerForm";

const { Header, Content, Footer, Sider } = Layout;
const { SubMenu } = Menu;

const StudentAvatar = ({name}) => {
	if (!name) {
		return <Avatar icon={<UserOutlined />}/>
	}
	let trim = name.trim();
	const split = trim.split(" ");
	if (split.length === 1) {
		return <Avatar>{name.charAt(0)}</Avatar>
	}
	return <Avatar>`${name.charAt(0)}``${name.charAt(name.length - 1)}`</Avatar>
}

const columns = fetchStudents => [
	{
		title: '',
		dataIndex: 'avatar',
		key: 'avatar',
		render: (text, student) => <StudentAvatar name={student.name} />
	},
	{
		title: 'Id',
		dataIndex: 'id',
		key: 'id',
	},
	{
		title: 'Name',
		dataIndex: 'name',
		key: 'name',
	},
	{
		title: 'Email',
		dataIndex: 'email',
		key: 'email',
	},
	{
		title: 'Gender',
		dataIndex: 'gender',
		key: 'gender',
	},
	{
		title: 'Actions',
		dataIndex:'actions',
		key: '',
		render: (text, student) =>
			<>
				<Popconfirm
					title={`Are you sure to delete ${student.name}`}
					onConfirm={() => deleteStudent(student.id, fetchStudents)}
					okText="Yes"
					cancelText="No"
				>
					<Button size="default">delete</Button>
				</Popconfirm>

				<Button size="default">edit</Button>
			</>
},
];


const antIcon = (
	<LoadingOutlined
		style={{
			fontSize: 24,
		}}
		spin
	/>
);

function App() {

	const [students, setStudents] = useState([]);
	const [collapsed, setCollapsed] = useState(false);
	const [fetching, setFetching] = useState(true);
	const [showDrawer, setShowDrawer] = useState(false);

   const fetchStudents = () =>
	   getAllStudents()
		   .then(res => res.json())
		   .then(data => {
				setStudents(data);
				setFetching(false);
		   })

   useEffect(() => {
		console.log('Component Mounted');
		fetchStudents();
   }, []);

	const addButton =
		<>
		<Button
			type="primary"
			shape="round"
			onClick={() => setShowDrawer(!showDrawer)}
			icon={<PlusOutlined/>}
			size="large">
			Add New Student
		</Button>
		<br/><br/>
		<Tag>Number of students</Tag>
		<Badge count={students.length} className="site-badge-count-4"/>
		</>;

	const renderStudents = () => {
		if (fetching) {
			return <Spin indicator={antIcon}/>;
		}
		if (students.length <= 0) {
			return <Empty/>;
		}
		return <>
			<StudentDrawerForm
				showDrawer={showDrawer}
				setShowDrawer={setShowDrawer}
				fetchStudents={fetchStudents}
			/>
			<Table
				dataSource={students}
				columns={columns(fetchStudents)}
				bordered
				title={() => addButton}
				pagination={{pageSize: 50}}
				scroll={{y: 1000}}
				rowKey={(student) => student.id}
			/>
		</>;
    }


	return <Layout style={{ minHeight: '100vh' }}>
		<Sider collapsible collapsed={collapsed}
			   onCollapse={setCollapsed}>
			<div className="logo" />
			<Menu theme="dark" defaultSelectedKeys={['1']} mode="inline">
				<Menu.Item key="1" icon={<PieChartOutlined />}>
					Option 1
				</Menu.Item>
				<Menu.Item key="2" icon={<DesktopOutlined />}>
					Option 2
				</Menu.Item>
				<SubMenu key="sub1" icon={<UserOutlined />} title="User">
					<Menu.Item key="3">Tom</Menu.Item>
					<Menu.Item key="4">Bill</Menu.Item>
					<Menu.Item key="5">Alex</Menu.Item>
				</SubMenu>
				<SubMenu key="sub2" icon={<TeamOutlined />} title="Team">
					<Menu.Item key="6">Team 1</Menu.Item>
					<Menu.Item key="8">Team 2</Menu.Item>
				</SubMenu>
				<Menu.Item key="9" icon={<FileOutlined />}>
					Files
				</Menu.Item>
			</Menu>
		</Sider>
		<Layout className="site-layout">
			<Header className="site-layout-background" style={{ padding: 0 }} />
			<Content style={{ margin: '0 16px' }}>
				<Breadcrumb style={{ margin: '16px 0' }}>
					<Breadcrumb.Item>User</Breadcrumb.Item>
					<Breadcrumb.Item>Bill</Breadcrumb.Item>
				</Breadcrumb>
				<div className="site-layout-background" style={{ padding: 24, minHeight: 360 }}>
					{renderStudents()}
				</div>
			</Content>
			<Footer style={{ textAlign: 'center' }}>Deneb1593 Design ©2022 Created by Deneb1593</Footer>
		</Layout>
	</Layout>;

}

export default App;
