-- Enable the NOCOUNT option to prevent the return of the count of affected rows by DML statements.
SET NOCOUNT ON;
GO
GO
-- Use the 'master' database to perform system management operations.
USE [master];
GO
GO
-- Check IF the 'Cp2396g01_group5_db' database exists, and drop it IF it does.
IF EXISTS (SELECT * FROM sys.databases WHERE name='Cp2396g01_group5_db')
BEGIN
    DROP DATABASE [Cp2396g01_group5_db];
END
GO
GO
-- Get the directory path of the master data file to use for the new 'Cp2396g01_group5_db' data and log files.
DECLARE @device_directory NVARCHAR(520);
SELECT @device_directory = SUBSTRING(physical_name, 1, CHARINDEX(N'master.mdf', LOWER(physical_name)) - 1)
FROM master.sys.master_files WHERE database_id = 1 AND file_id = 1;

-- Create the 'Cp2396g01_group5_db' database with primary data file and log file locations.
EXECUTE ('CREATE DATABASE Cp2396g01_group5_db
  ON PRIMARY (NAME = N''Cp2396g01_group5_db_v1'', FILENAME = N''' + @device_directory + N'Cp2396g01_group5_db_v1.mdf'')
  LOG ON (NAME = N''Cp2396g01_group5_db_log_v1'',  FILENAME = N''' + @device_directory + N'Cp2396g01_group5_db_v1.ldf'')');
GO
GO

-- Set options for the 'Cp2396g01_group5_db' database depending on the SQL Server version.
IF CAST(SERVERPROPERTY('ProductMajorVersion') AS INT) < 12 
BEGIN
  -- For versions earlier than SQL Server 2014
  ALTER DATABASE [Cp2396g01_group5_db] SET RECOVERY SIMPLE WITH NO_WAIT;
  ALTER DATABASE [Cp2396g01_group5_db] SET AUTO_UPDATE_STATISTICS_ASYNC OFF;
END
ELSE 
BEGIN
  -- For SQL Server 2014 and later
  ALTER DATABASE [Cp2396g01_group5_db] SET RECOVERY SIMPLE WITH NO_WAIT;
  ALTER DATABASE [Cp2396g01_group5_db] SET AUTO_UPDATE_STATISTICS_ASYNC OFF;
END
GO
GO

-- Set quoted identIFy for database
SET quoted_identIFier ON
GO

-- Set the date format to month/day/year.
SET DATEFORMAT mdy;
GO

-- Set the compatibility level for the 'Cp2396g01_group5_db' database.
ALTER DATABASE [Cp2396g01_group5_db] SET COMPATIBILITY_LEVEL = 150;
GO
GO
-- IF Full-Text Indexing is installed, enable it for the 'Cp2396g01_group5_db' database.
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
BEGIN
  EXEC [Cp2396g01_group5_db].[dbo].[sp_fulltext_database] @action = 'enable';
END
GO
GO

-- Set ANSI and ARITHABORT options for the 'Cp2396g01_group5_db' database.
ALTER DATABASE [Cp2396g01_group5_db] SET ANSI_NULL_DEFAULT OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET ANSI_NULLS OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET ANSI_PADDING OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET ANSI_WARNINGS OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET ARITHABORT OFF;
GO
GO

-- Set various options for the 'Cp2396g01_group5_db' database.
-- (ALTER DATABASE statements are alphabetically ordered for clarity)
ALTER DATABASE [Cp2396g01_group5_db] SET ALLOW_SNAPSHOT_ISOLATION OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET AUTO_CLOSE OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET AUTO_SHRINK OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET AUTO_UPDATE_STATISTICS ON;
ALTER DATABASE [Cp2396g01_group5_db] SET DATE_CORRELATION_OPTIMIZATION OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET DB_CHAINING OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET DELAYED_DURABILITY = DISABLED;
ALTER DATABASE [Cp2396g01_group5_db] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF );
ALTER DATABASE [Cp2396g01_group5_db] SET HONOR_BROKER_PRIORITY OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET MULTI_USER;
ALTER DATABASE [Cp2396g01_group5_db] SET NUMERIC_ROUNDABORT OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET PAGE_VERIFY CHECKSUM;
ALTER DATABASE [Cp2396g01_group5_db] SET PARAMETERIZATION SIMPLE;
ALTER DATABASE [Cp2396g01_group5_db] SET QUOTED_IDENTIFIER OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET QUERY_STORE = OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET READ_COMMITTED_SNAPSHOT OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET RECURSIVE_TRIGGERS OFF;
ALTER DATABASE [Cp2396g01_group5_db] SET TARGET_RECOVERY_TIME = 60 SECONDS;
ALTER DATABASE [Cp2396g01_group5_db] SET TRUSTWORTHY OFF;
GO
GO

-- Switch to the 'Cp2396g01_group5_db' database to perform subsequent commands.
USE [Cp2396g01_group5_db];
GO
GO

-- Check exists entity in the database 'Cp2396g01_group5_db'
IF exists (select * from sysobjects where id = object_id('dbo.Sales by Year') and sysstat & 0xf = 4)
	drop procedure [dbo].[Sales by Year]
GO

IF exists (select * from sysobjects where id = object_id('dbo.OrderByQuarter') and sysstat & 0xf = 2)
	drop view [dbo].[OrderByQuarter]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Sales Totals by Amount') and sysstat & 0xf = 2)
	drop view [dbo].[Sales Totals by Amount]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Invoices') and sysstat & 0xf = 2)
	drop view [dbo].[Invoices]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Order Subtotals') and sysstat & 0xf = 2)
	drop view [dbo].[Order Subtotals]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Employee_Blog') and sysstat & 0xf = 3)
	drop table [dbo].[Employee_Blog]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Blogs') and sysstat & 0xf = 3)
	drop table [dbo].[Blogs]
GO

IF exists (select * from sysobjects where id = object_id('dbo.ImportOrder') and sysstat & 0xf = 3)
	drop table [dbo].[ImportOrder]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Branch') and sysstat & 0xf = 3)
	drop table [dbo].[Branch]
GO

IF exists (select * from sysobjects where id = object_id('dbo.ThresholdAdjustment') and sysstat & 0xf = 3)
	drop table [dbo].[ThresholdAdjustment]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Manager_Genders') and sysstat & 0xf = 3)
	drop table [dbo].[Manager_Genders]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Employee_Genders') and sysstat & 0xf = 3)
	drop table [dbo].[Employee_Genders]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Customer_Genders') and sysstat & 0xf = 3)
	drop table [dbo].[Customer_Genders]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Customer_Card') and sysstat & 0xf = 3)
	drop table [dbo].[Customer_Card]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Cards') and sysstat & 0xf = 3)
	drop table [dbo].[Cards]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Managers') and sysstat & 0xf = 3)
	drop table [dbo].[Managers]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Genders') and sysstat & 0xf = 3)
	drop table [dbo].[Genders]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Customer_Feedback') and sysstat & 0xf = 3)
	drop table [dbo].[Customer_Feedback]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Feedbacks') and sysstat & 0xf = 3)
	drop table [dbo].[Feedbacks]
GO

IF exists (select * from sysobjects where id = object_id('dbo.OrderDetails') and sysstat & 0xf = 3)
	drop table [dbo].[OrderDetails]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Orders') and sysstat & 0xf = 3)
	drop table [dbo].[Orders]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Employees') and sysstat & 0xf = 3)
	drop table [dbo].[Employees]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Products') and sysstat & 0xf = 3)
	drop table [dbo].[Products]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Customers') and sysstat & 0xf = 3)
	drop table [dbo].[Customers]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Permissions') and sysstat & 0xf = 3)
	drop table [dbo].[Permissions]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Brand') and sysstat & 0xf = 3)
	drop table [dbo].[Brand]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Categories') and sysstat & 0xf = 3)
	drop table [dbo].[Categories]
GO

IF exists (select * from sysobjects where id = object_id('dbo.InventoryStatus') and sysstat & 0xf = 3)
	drop table [dbo].[InventoryStatus]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Suppliers') and sysstat & 0xf = 3)
	drop table [dbo].[Suppliers]
GO

IF EXISTS (SELECT * FROM sysobjects WHERE id = object_id('dbo.trg_Products_UpdateQuantityInStock') AND OBJECTPROPERTY(id, 'IsTrigger') = 1)
	DROP TRIGGER [dbo].[trg_Products_UpdateQuantityInStock]
GO

IF EXISTS (SELECT * FROM sysobjects WHERE id = object_id('dbo.trg_UpdateRestockThreshold') AND OBJECTPROPERTY(id, 'IsTrigger') = 1)
	DROP TRIGGER [dbo].[trg_trg_UpdateRestockThreshold]
GO

IF EXISTS (SELECT * FROM sysobjects WHERE id = object_id('dbo.trg_Group_Manager') AND OBJECTPROPERTY(id, 'IsTrigger') = 1)
	DROP TRIGGER [dbo].[trg_Group_Manager]
GO

IF EXISTS (SELECT * FROM sysobjects WHERE id = object_id('dbo.trg_Group_Employee') AND OBJECTPROPERTY(id, 'IsTrigger') = 1)
	DROP TRIGGER [dbo].[trg_Group_Employee]
GO

IF EXISTS (SELECT * FROM sysobjects WHERE id = object_id('dbo.trg_Group_Customer') AND OBJECTPROPERTY(id, 'IsTrigger') = 1)
	DROP TRIGGER [dbo].[trg_Group_Customer]
GO

IF EXISTS (SELECT * FROM sysobjects WHERE id = object_id('dbo.trg_SetTotalPrice') AND OBJECTPROPERTY(id, 'IsTrigger') = 1)
	DROP TRIGGER [dbo].[trg_SetTotalPrice]
GO

IF EXISTS (SELECT * FROM sysobjects WHERE id = object_id('dbo.Trigger_UpdateInventoryStatus') AND OBJECTPROPERTY(id, 'IsTrigger') = 1)
	DROP TRIGGER [dbo].[Trigger_UpdateInventoryStatus]
GO

-- Create entity for the database 'Cp2396g01_group5_db'

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create table PERMISSION--
CREATE TABLE [dbo].[Permissions](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[permissionName] [nvarchar](50) NULL,
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Permission" PRIMARY KEY  CLUSTERED 
	(
		"ID"ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
)ON [Primary]

GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Genders--
CREATE TABLE [dbo].[Genders](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[genderName] [nvarchar](50) NULL,	
	[description] [nvarchar](255) NULL,
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Genders" PRIMARY KEY  CLUSTERED 
	(
		"ID"ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

)ON [Primary]

GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Manager--
CREATE TABLE [dbo].[Managers](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[manager_gender] [bigint] NULL,
	[manager_group] [bigint] CONSTRAINT "DF_Perr_Mana" DEFAULT (1),
	------------------------------
	[username] [nvarchar](50) NULL,
	[password] [nvarchar](max) NULL,
	[salt_password] [nvarchar](max) NULL,
	[pepper_password][nvarchar](16) NULL,
	--------------------------------
	[fullname] [nvarchar](50) NULL,	
	[address] [nvarchar](255) NULL,
	[phone] [nvarchar](50) NULL,
	[email] [nvarchar](255) NULL,
	[imageURL] [nvarchar](max) NULL,
	-------------------------------
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](50) NULL,
	[modifieby] [nvarchar](50) NULL,
	[status] [bit] NULL CONSTRAINT "DF_Status_Mana" DEFAULT (1),
	CONSTRAINT "PK_Managers" PRIMARY KEY  CLUSTERED 
	(
		"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Manager_Group" FOREIGN KEY 
	(
		"manager_group"
	) REFERENCES "dbo"."Permissions" (
		"ID"
	),	
)ON [Primary]

GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Manager_Genders--
CREATE TABLE [dbo].[Manager_Genders](
	[manager_ID] [bigint] NOT NULL,
	[gender_ID] [bigint] NOT NULL,	
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Manager_Genders" PRIMARY KEY  CLUSTERED 
	(
		"manager_ID",
		"gender_ID"
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
	CONSTRAINT "FK_ManagerGender_Manager" FOREIGN KEY 
	(
		"manager_ID"
	) REFERENCES "dbo"."Managers" (
		"ID"
	),
	CONSTRAINT "FK_ManagerGender_Gender" FOREIGN KEY 
	(
		"gender_ID"
	) REFERENCES "dbo"."Genders" (
		"ID"
	),

)ON [Primary]

GO


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Employee--
CREATE TABLE [dbo].[Employees](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[employee_gender] [bigint] NULL,
	[employee_group] [bigint] CONSTRAINT "DF_Perr_Emp" DEFAULT (2),
	-----------------------------
	[username] [nvarchar](50) NULL,
	[password] [nvarchar](max) NULL,
	[salt_password] [nvarchar](max) NULL,
	[pepper_password][nvarchar](16) NULL,
	-------------------------------
	[fullname] [nvarchar](50) NULL,
	[address] [nvarchar](255) NULL,
	[phone] [nvarchar](50) NULL,
	[email] [nvarchar](255) NULL,
	[birthDate] [datetime] NULL ,
	[notes] [nvarchar](max) NULL,
	[imageURL] [nvarchar](max) NULL,
	------------------------------
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](50) NULL,
	[modifieby] [nvarchar](50) NULL,
	[status] [bit] NULL CONSTRAINT "DF_Status_Emp" DEFAULT (1),
	CONSTRAINT "PK_Employee" PRIMARY KEY CLUSTERED
	(
	"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Employee_Group" FOREIGN KEY 
	(
		"employee_group"
	) REFERENCES "dbo"."Permissions" (
		"ID"
	),

	CONSTRAINT "CK_Birthdate" CHECK (birthDate < getdate())
)ON [Primary]

GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Employee_Genders--
CREATE TABLE [dbo].[Employee_Genders](
	[employee_ID] [bigint] NOT NULL,
	[gender_ID] [bigint] NOT NULL,	
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Employee_Genders" PRIMARY KEY  CLUSTERED 
	(
		"employee_ID",
		"gender_ID"
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
	CONSTRAINT "FK_EmployeeGender_Employee" FOREIGN KEY 
	(
		"employee_ID"
	) REFERENCES "dbo"."Employees" (
		"ID"
	),
	CONSTRAINT "FK_EmployeeGender_Gender" FOREIGN KEY 
	(
		"gender_ID"
	) REFERENCES "dbo"."Genders" (
		"ID"
	),

)ON [Primary]

GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Customers--
CREATE TABLE [dbo].[Customers](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[customer_gender] [bigint] NULL,
	[customer_card] [bigint] NULL,
	[customer_group] [bigint] CONSTRAINT "DF_Perr_Cus" DEFAULT (3),
	------------------------------
	[username] [nvarchar](50) NULL,
	[password] [nvarchar](max) NULL,
	[salt_password] [nvarchar](max) NULL,
	[pepper_password][nvarchar](16) NULL,
	-------------------------------
	[fullname] [nvarchar](255) NULL,
	[address] [nvarchar](50) NULL,
	[phone] [nvarchar](50) NULL,
	[email] [nvarchar](255) NULL,
	[birthDate] [datetime] NULL ,
	[imageURL] [nvarchar](255) NULL,
	-------------------------------
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](50) NULL,
	[modifieby] [nvarchar](50) NULL,
	[status] [bit] NULL CONSTRAINT "DF_Status_Cus" DEFAULT (1),
	CONSTRAINT "PK_Customer" PRIMARY KEY CLUSTERED
	(
	"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],	
	CONSTRAINT "FK_Customer_Group" FOREIGN KEY 
	(
		"customer_group"
	) REFERENCES "dbo"."Permissions" (
		"ID"
	),
	
	CONSTRAINT "CK_Birthdate_Customer" CHECK (birthDate < getdate()),
	
)ON [Primary]

GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Cards--
CREATE TABLE [dbo].[Cards](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[cardName] [nvarchar](255) NULL,
	[cardNumber] [varchar](16) NULL,
	[valueFrom] [datetime] NULL,
	[expirationDate] [dateTime] NULL,
	[cvvNumber] [int] NULL,
	[cardType] [nvarchar](20) NULL,
	[point] [bigint] NULL,
	[rank] [nvarchar](50) NULL,
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Card" PRIMARY KEY  CLUSTERED 
	(
		"ID" ASC		
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
	CONSTRAINT "CK_cvvNumber" CHECK (LEN(cvvNumber) = 3 AND cvvNumber LIKE '[0-9][0-9][0-9]'),
	CONSTRAINT "CK_CardNumberFormat" CHECK (LEN(cardNumber) = 16 AND cardNumber LIKE '[0-9][0-9][0-9][0-9] [0-9][0-9][0-9][0-9] [0-9][0-9][0-9][0-9] [0-9][0-9][0-9][0-9]')

)ON [Primary]

GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Customer_Card--
CREATE TABLE [dbo].[Customer_Card](
	[customer_ID] [bigint] NOT NULL,
	[card_ID] [bigint] NOT NULL,	
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Customer_Card" PRIMARY KEY  CLUSTERED 
	(
		"customer_ID",
		"card_ID"
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
	CONSTRAINT "FK_CustomerCard_Customer" FOREIGN KEY 
	(
		"customer_ID"
	) REFERENCES "dbo"."Customers" (
		"ID"
	),
	CONSTRAINT "FK_CustomerCard_Card" FOREIGN KEY 
	(
		"card_ID"
	) REFERENCES "dbo"."Cards" (
		"ID"
	),

)ON [Primary]

GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Customer_Genders--
CREATE TABLE [dbo].[Customer_Genders](
	[customer_ID] [bigint] NOT NULL,
	[gender_ID] [bigint] NOT NULL,	
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Customer_Genders" PRIMARY KEY  CLUSTERED 
	(
		"customer_ID",
		"gender_ID"
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
	CONSTRAINT "FK_CustomerGender_Customer" FOREIGN KEY 
	(
		"customer_ID"
	) REFERENCES "dbo"."Customers" (
		"ID"
	),
	CONSTRAINT "FK_CustomerGender_Gender" FOREIGN KEY 
	(
		"gender_ID"
	) REFERENCES "dbo"."Genders" (
		"ID"
	),

)ON [Primary]

GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Blogs--
CREATE TABLE [dbo].[Blogs](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[employee_ID] [bigint] NULL,
	[title] [nvarchar](255) NULL,
	[imageURL] [nvarchar](max) NULL,
	[content] [nvarchar](max) NULL,
	[category] [nvarchar](255) NULL,
	[sortcontent] [nvarchar](max) NULL,
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Feedback" PRIMARY KEY  CLUSTERED 
	(
		"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

)ON [Primary]

GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Employee_Blog--
CREATE TABLE [dbo].[Employee_Blog](
	[employee_ID] [bigint] NOT NULL,
	[blog_ID] [bigint] NOT NULL,	
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Employee_Blog" PRIMARY KEY  CLUSTERED 
	(
		"employee_ID",
		"blog_ID"
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
	CONSTRAINT "FK_EmployeeBlog_Employee" FOREIGN KEY 
	(
		"employee_ID"
	) REFERENCES "dbo"."Employees" (
		"ID"
	),
	CONSTRAINT "FK_EmployeeBlog_Blog" FOREIGN KEY 
	(
		"blog_ID"
	) REFERENCES "dbo"."Blogs" (
		"ID"
	),

)ON [Primary]

GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table FeedBacks--
CREATE TABLE [dbo].[Feedbacks](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[customer_ID] [bigint] NOT NULL,
	[title] [nvarchar](255) NOT NULL,
	[content] [nvarchar](max) NULL,
	[date] [datetime] NULL,
	[rate] [int] NULL,
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Feedback_Cus" PRIMARY KEY  CLUSTERED 
	(
		"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_FeedBack_Customer" FOREIGN KEY 
	(
		"customer_ID"
	) REFERENCES "dbo"."Customers" (
		"ID"
	),
)ON [Primary]

GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create TableCustomer_Feedback--
CREATE TABLE [dbo].[Customer_Feedback](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[customer_ID] [bigint] NOT NULL,
	[feedback_ID] [bigint] NOT NULL,	
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Customer_Feedback" PRIMARY KEY  CLUSTERED 
	(
		"customer_ID",
		"feedback_ID"
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
	CONSTRAINT "FK_CustomerFeedback_Customer" FOREIGN KEY 
	(
		"customer_ID"
	) REFERENCES "dbo"."Customers" (
		"ID"
	),
	CONSTRAINT "FK_CustomerFeedback_Feedback" FOREIGN KEY 
	(
		"feedback_ID"
	) REFERENCES "dbo"."Feedbacks" (
		"ID"
	),
)ON [Primary]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Suppliers--
CREATE TABLE [dbo].[Suppliers](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[companyName] [nvarchar] (255) NULL,
	[contactName] [nvarchar] (255) NULL,
	[contactTitle] [nvarchar] (255) NULL,
	[address] [nvarchar](255) NULL,
	[city] [nvarchar](15) NULL,
	[postalCode] [nvarchar](10) NULL,
	[phone] [nvarchar](24) NULL,
	[fax] [nvarchar](24) NULL,
	[homePage] [nvarchar](max) NULL,
	[description] [nvarchar](255) NULL,
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Supplier" PRIMARY KEY CLUSTERED
	(
	"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
)ON [Primary]

GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Brand--
CREATE TABLE [dbo].[Brand](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[supplierID] [bigint] NOT NULL,
	[brandName] [nvarchar](255) NOT NULL,
	[address] [nvarchar](255) NULL,
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Brand" PRIMARY KEY CLUSTERED
	(
	"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
	CONSTRAINT "FK_Brand_Supplier" FOREIGN KEY 
	(
		"supplierID"
	) REFERENCES "dbo"."Suppliers" (
		"ID"
	),
)ON [Primary]

GO

--SET ANSI_NULLS ON
--GO
--SET QUOTED_IDENTIFIER ON
--GO
----Create Table Warehouse--
--CREATE TABLE [dbo].[Warehouse](
--	[ID] [bigint] IDENTITY(1,1) NOT NULL,
--	[companyName] [nvarchar] (40) NULL,
--	[warehouseName] [nvarchar](50)  NULL,
--	[address] [nvarchar](255) NULL,
--	[city] [nvarchar](15) NULL,
--	[phone] [nvarchar](24) NULL,
--	[fax] [nvarchar](24) NULL,
--	[postalCode] [nvarchar](10) NULL,
--	[description] [nvarchar](255) NULL,
--	[createdate] [datetime] NULL,
--	[modifiedate] [datetime] NULL,
--	[createby] [nvarchar](255) NULL,
--	[modifieby] [nvarchar](255) NULL
--	CONSTRAINT "PK_Warehouse" PRIMARY KEY CLUSTERED
--	(
--	"ID" ASC
--	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
--)ON [Primary]

--GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Branch--
CREATE TABLE [dbo].[Branch](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[companyName] [nvarchar] (40) NULL,
	[branchName] [nvarchar](50)  NULL,
	[address] [nvarchar](255) NULL,
	[city] [nvarchar](15) NULL,
	[phone] [nvarchar](24) NULL,
	[fax] [nvarchar](24) NULL,
	[postalCode] [nvarchar](10) NULL,
	[description] [nvarchar](255) NULL,
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
CONSTRAINT "PK_Branch" PRIMARY KEY CLUSTERED
	(
	"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
)ON [Primary]

GO
 
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Categories--
CREATE TABLE [dbo].[Categories](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[categoryName] [nvarchar](50) NULL,
	[restockThreshold] [int] NULL,
	[newAdjustment] [int]NULL CONSTRAINT "DF_Products_AdjustmentThreshold" DEFAULT (0),
	[description] [nvarchar](255) NULL,
	CONSTRAINT "PK_Category" PRIMARY KEY CLUSTERED
	(
	"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
)ON [Primary]

GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Import--
	CREATE TABLE [dbo].[ImportOrder](
		[ID] [bigint] IDENTITY(1,1) NOT NULL,
		[supplierID] [bigint] NOT NULL,
		[categoryID] [bigint] NOT NULL,
		[branchID] [bigint] NOT NULL,
		[companyName] [nvarchar] (40) NULL,
		[dateImport] [datetime] NULL,
		[hsCode] [int] NULL,
		[nameProduct] [nvarchar](255) NULL,
		[amountDelivery] [int] NULL,
		[address] [nvarchar](255) NULL,
		[city] [nvarchar](15) NULL,
		[phone] [nvarchar](24) NULL,
		[fax] [nvarchar](24) NULL,
		[postalCode] [nvarchar](10) NULL,
		[description] [nvarchar](255) NULL,
		[status] [nvarchar](50) NULL,
		[leadtime] [int] NULL,
		[createdate] [datetime] NULL,
		[modifiedate] [datetime] NULL,
		[createby] [nvarchar](255) NULL,
		[modifieby] [nvarchar](255) NULL
		CONSTRAINT "PK_Import" PRIMARY KEY CLUSTERED
		(
		"ID" ASC
		)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

		CONSTRAINT "FK_Import_Supplier" FOREIGN KEY 
		(
			"supplierID"
		) REFERENCES "dbo"."Suppliers" (
			"ID"
		),

		CONSTRAINT "FK_Import_Category" FOREIGN KEY 
		(
			"categoryID"
		) REFERENCES "dbo"."Categories" (
			"ID"
		),
		CONSTRAINT "FK_Import_Branch" FOREIGN KEY 
		(
			"branchID"
		) REFERENCES "dbo"."Branch" (
			"ID"
		),

	)ON [Primary]

GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Inventory Status--
CREATE TABLE InventoryStatus
(
    [ID] [BIGINT] identity(1,1) NOT NULL,
    [statusName] [NVARCHAR](50) NULL,
	CONSTRAINT "PK_Status" PRIMARY KEY CLUSTERED
	(
	"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

)ON [Primary]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Products--
CREATE TABLE [dbo].[Products](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,	
	[categoryID] [bigint] NOT NULL,
	[brandID] [bigint] NOT NULL,
	[supplierID] [bigint] NOT NULL,
	[productName] [nvarchar](255) NULL,
	[imageURL] [nvarchar](max) NULL,
	[unitPrice] [money] NULL CONSTRAINT "DF_Products_UnitPrice" DEFAULT (0),
	[quantity] [int] NULL,
	[quantityInStock] [int] NULL,
	[unit] [nvarchar](50) NULL,
	[description] [nvarchar](max) NULL,
	[sortdescription] [nvarchar](max) NULL,
	[viewCount] [int] NULL,
	[discount] [int] NULL,
	[avaliable] [bit] NULL,
	[inventoryStatus] [bigint] NOT NULL CONSTRAINT "DF_Inventory" DEFAULT (1),
	[createdate] [datetime] NULL,
	[modifiedate] [datetime] NULL,
	[createby] [nvarchar](255) NULL,
	[modifieby] [nvarchar](255) NULL
	CONSTRAINT "PK_Products" PRIMARY KEY  CLUSTERED 
	(
		"ID"
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Products_Categories" FOREIGN KEY 
	(
		"categoryID"
	) REFERENCES "dbo"."Categories" (
		"ID"
	),
	CONSTRAINT "FK_Products_Brands" FOREIGN KEY 
	(
		"brandID"
	) REFERENCES "dbo"."Brand" (
		"ID"
	),
	CONSTRAINT "FK_Products_Suppliers" FOREIGN KEY 
	(
		"supplierID"
	) REFERENCES "dbo"."Suppliers" (
		"ID"
	),
	CONSTRAINT "FK_Products_InventoryStatus" FOREIGN KEY 
	(
		"inventoryStatus"
	) REFERENCES "dbo"."InventoryStatus" (
		"ID"
	),

)On [Primary]

GO


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table ThAdj--
CREATE TABLE [dbo].[ThresholdAdjustment](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[categoryID] [bigint] NOT NULL,
	[reasonAdjustment] [nvarchar](50) NULL,
	[new_restockThreshold] [int] NULL,
	[dateAdjusted] [datetime] NULL,
	[statusThresholdAdjustments] [nvarchar](50) NULL,
	CONSTRAINT "PK_Adjustment" PRIMARY KEY CLUSTERED
	(
	"ID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Adjustment_WP" FOREIGN KEY 
	(
		"categoryID"
	) REFERENCES "dbo"."Categories" (
		"ID"
	),
)On [Primary]

GO


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Orders--
CREATE TABLE dbo.Orders (
    [ID] [bigINT] IDENTITY(1,1) NOT NULL,
    [customerID] [bigINT] NOT NULL,
	[employeeID] [bigINT] NOT NULL,   	
	[paymentMethod][nvarchar](255) NULL,
	[note] [nvarchar](max) NULL,
    [status] [int] NULL,
	CONSTRAINT "PK_Orders" PRIMARY KEY CLUSTERED 
	(
	ID ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

    CONSTRAINT "FK_Orders_Customers" FOREIGN KEY ("customerID") REFERENCES "dbo"."Customers"("ID"),
	CONSTRAINT "FK_Orders_Employees" FOREIGN KEY ("employeeID") REFERENCES "dbo"."Employees"("ID")
)ON [Primary]

GO


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table OrderDetails
CREATE TABLE [dbo].[OrderDetails](	
	[orderID] [bigint]NOT NULL,
	[productID][bigint]NOT NULL,
	[unitPrice] [money] NULL,
	[quantity] [int]NULL,
	[discount] [int]NULL,
	[totalPrice] [money] NULL,
	[orderDate] [DATETIME] NULL,
    [shipDate] [DATETIME] NULL,
	[shipAddress] [nvarchar](255) NULL,
	[paymentMethod][nvarchar](255) NULL,
	CONSTRAINT "PK_Order_Details" PRIMARY KEY  CLUSTERED 
	(
		"orderID",
		"productID"
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Order_Details_Orders" FOREIGN KEY 
	(
		"orderID"
	) REFERENCES "dbo"."Orders" (
		"ID"
	),
	CONSTRAINT "FK_Order_Details_Pro" FOREIGN KEY 
	(
		"productID"
	) REFERENCES "dbo"."Products" (
		"ID"
	),
)

GO

-- Create view OrderByQuarter
CREATE VIEW OrderByQuarter AS
SELECT
    ID,
    CustomerID,
    EmployeeID,
    OrderDate,
    ShipDate,
    ShipAddress,
    Note,
    Status,
    FullName,
    Address,
    Phone,
    Email,
    Point,
    Rank,
    CASE
        WHEN MONTH(OrderDate) BETWEEN 1 AND 3 THEN 'Q1'
        WHEN MONTH(OrderDate) BETWEEN 4 AND 6 THEN 'Q2'
        WHEN MONTH(OrderDate) BETWEEN 7 AND 9 THEN 'Q3'
        WHEN MONTH(OrderDate) BETWEEN 10 AND 12 THEN 'Q4'
        ELSE NULL
    END AS Quarter
FROM
    (
        -- Your existing join with Customers and CreditCard
        SELECT
            Orders.ID,
            Orders.CustomerID,
            Orders.EmployeeID,
            OrderDetails.OrderDate,
            OrderDetails.ShipDate,
            OrderDetails.ShipAddress,
            Orders.Note,
            Orders.Status,
            Customers.FullName,
            Customers.Address,
            Customers.Phone,
            Customers.Email,
            Cards.Point,
            Cards.Rank
         FROM
            Customers
            INNER JOIN Orders ON Customers.ID = Orders.CustomerID
            INNER JOIN Customer_Card ON Customer_Card.customer_ID = Customers.ID
            INNER JOIN Cards ON Cards.ID = Customer_Card.card_ID
            INNER JOIN OrderDetails ON OrderDetails.orderID = Orders.ID
    ) AS Subquery;

GO
GO

-- Create view Invoices
CREATE VIEW Invoices AS
SELECT 
    o.ID AS 'Invoice Number',
    od.orderDate AS 'Order Date',
    c.fullName AS 'Customer Name',
    c.address AS 'Customer Address',
    c.phone AS 'Customer Phone',
    od.productID AS 'Product ID',
    p.productName AS 'Product Name',
    od.unitPrice AS 'Unit Price',
    od.quantity AS 'Quantity',
    od.discount AS 'Discount (%)',
    od.totalPrice AS 'Line Total',
    od.shipAddress AS 'Shipping Address',
    o.note AS 'Notes',
    o.status AS 'Order Status',
    od.paymentMethod AS 'Payment Method',
    cd.cardName AS 'Card Name',
    cd.cardNumber AS 'Card Number (last 4 digits)',
    (SELECT SUM(totalPrice) FROM OrderDetails WHERE orderID = o.ID) AS 'Invoice Total'
FROM Orders o
INNER JOIN Customers c ON o.customerID = c.ID
INNER JOIN OrderDetails od ON o.ID = od.orderID
INNER JOIN Products p ON od.productID = p.ID
INNER JOIN Customer_Card cc ON cc.customer_ID = c.ID
INNER JOIN Cards cd ON cd.ID = cc.card_ID
WHERE o.status = 'Successful';

GO

-- Create view Order Subtotals
CREATE VIEW "Order Subtotals" AS
SELECT
    OD.OrderID,
    SUM(CONVERT(money, (P.unitPrice * OD.quantity * (1 - OD.Discount) / 100)) * 100) AS Subtotal
FROM
    dbo.OrderDetails AS OD
JOIN
    dbo.Products AS P ON P.ID = OD.productID
GROUP BY
    OD.OrderID;
GO
GO

-- Create view Sales Totals by Amount
CREATE VIEW "Sales Totals by Amount" AS
SELECT 
    SUM("Order Subtotals".Subtotal) AS SaleAmount, 
    Orders.ID, 
    Customers.fullname, 
    OrderDetails.ShipDate,
    DATEPART(QUARTER, OrderDetails.ShipDate) AS Quarter
FROM 
    Customers 
    INNER JOIN Orders ON Customers.ID = Orders.CustomerID
	INNER JOIN OrderDetails ON Orders.ID = OrderDetails.orderID
    INNER JOIN "Order Subtotals" ON Orders.ID = "Order Subtotals".OrderID
WHERE 
    ("Order Subtotals".Subtotal > 2500)
GROUP BY 
    Orders.ID, 
    Customers.fullname, 
    OrderDetails.ShipDate, 
    DATEPART(QUARTER, OrderDetails.ShipDate);
GO
GO

-- Create procedure Sales by Year
create procedure "Sales by Year" 
	@Beginning_Date DateTime, @Ending_Date DateTime AS
SELECT OrderDetails.ShipDate, Orders.ID, "Order Subtotals".Subtotal, DATENAME(yy,ShipDate) AS Year
FROM Orders 
INNER JOIN "Order Subtotals" ON Orders.ID = "Order Subtotals".OrderID
INNER JOIN OrderDetails ON Orders.ID = OrderDetails.orderID
WHERE OrderDetails.ShipDate Between @Beginning_Date And @Ending_Date
GO
GO

------Enforce quantity <= quantityInStock------
CREATE TRIGGER trg_Products_UpdateQuantityInStock
ON Products
AFTER INSERT, UPDATE
AS
BEGIN
    IF UPDATE(quantity)
    BEGIN
        UPDATE Products
        SET quantityInStock = quantity
        WHERE ID IN (SELECT ID FROM inserted);
    END
END;
GO
 
 -----------Enforce restockThreshold in Categories table----------
CREATE TRIGGER trg_UpdateRestockThreshold
ON ThresholdAdjustment
AFTER INSERT
AS
BEGIN
    IF UPDATE(statusThresholdAdjustments)
        AND EXISTS (SELECT 1 FROM inserted WHERE statusThresholdAdjustments = 'Accept')
    BEGIN
        UPDATE Categories
        SET restockThreshold = newAdjustment,  -- Update restockThreshold using newAdjustment
            newAdjustment = i.new_restockThreshold  -- Capture new_restockThreshold for future reference
        FROM inserted i
        WHERE Categories.ID = i.categoryID;
    END
END;
GO

------Enforce group manager------
CREATE TRIGGER trg_Group_Manager
ON Managers AFTER INSERT AS BEGIN
UPDATE Managers
SET
  manager_group = 1
FROM
  Managers
  INNER JOIN inserted ON Managers.ID = inserted.ID END;
GO

------Enforce group employee------
CREATE TRIGGER trg_Group_Employee
ON Employees AFTER INSERT AS BEGIN
UPDATE Employees
SET
  employee_group = 2
FROM
  Employees
  INNER JOIN inserted ON Employees.ID = inserted.ID END;
GO

------Enforce group customer------
CREATE TRIGGER trg_Group_Customer
ON Customers AFTER INSERT AS BEGIN
UPDATE Customers
SET
  customer_group = 3
FROM
  Customers
  INNER JOIN inserted ON Customers.ID = inserted.ID END;
GO

------Enforce UpdateInventoryStatus------
CREATE TRIGGER Trigger_UpdateInventoryStatus ON Products AFTER INSERT,
UPDATE AS BEGIN
UPDATE Products
SET
  inventoryStatus = CASE
    WHEN Products.quantityInStock < 5 THEN 3
    WHEN Products.quantityInStock >= 50 THEN 2
    WHEN Products.quantityInStock > 100 THEN 1
  END
FROM
  Products
  INNER JOIN inserted ON Products.ID = inserted.ID;
END;
GO

------Enforce SetTotalPrice------
CREATE TRIGGER trg_SetTotalPrice ON dbo.OrderDetails AFTER INSERT,
UPDATE AS BEGIN
UPDATE dbo.OrderDetails
SET
  totalPrice = OrderDetails.quantity * OrderDetails.unitPrice * ((100 - OrderDetails.discount) / 100)
FROM
  dbo.OrderDetails
  INNER JOIN inserted ON dbo.OrderDetails.orderID = inserted.orderID
  AND dbo.OrderDetails.productID = inserted.productID END;
 GO
 
 --=======================INSERT DATA=====================--
set quoted_identifier on
go
ALTER TABLE "Permissions" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Permissions" ("permissionName") VALUES ('manager');
INSERT INTO "Permissions" ("permissionName") VALUES ('employee');
INSERT INTO "Permissions" ("permissionName") VALUES ('customer');
GO
ALTER TABLE "Permissions" CHECK CONSTRAINT ALL
go
GO

---------------------------------------------
set quoted_identifier on
go
ALTER TABLE "Genders" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Genders" ("genderName", "description") VALUES ('Male', 'Male');
INSERT INTO "Genders" ("genderName", "description") VALUES ('FeMale', 'FeMale');
INSERT INTO "Genders" ("genderName", "description") VALUES ('Other', 'Other');
INSERT INTO "Genders" ("genderName", "description") VALUES ('Null', 'Not Set');
GO
ALTER TABLE "Genders" CHECK CONSTRAINT ALL
go
GO

---------------------------------------------
--set quoted_identifier on
--go
--ALTER TABLE "Managers" NOCHECK CONSTRAINT ALL
--go
--INSERT INTO "Managers" ("fullname","accountID","address","email","imageURL","phone") VALUES ('Manager',1,'Can Tho','manager.zipmart@gamil.com','https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/een5b4xmq5twvne8ce0c',' 0292 3731 072');
--GO
--ALTER TABLE "Managers" CHECK CONSTRAINT ALL
--go
--GO

---------------------------------------------
--set quoted_identifier on
--go
--ALTER TABLE "Employees" NOCHECK CONSTRAINT ALL
--go
--INSERT INTO "Employees" ("fullname","accountID","imageURL","birthDate","address","email","phone","Notes") VALUES ('To Quang Tuong',2,'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/pjfdcg0pm9beusibmr3h','07-25-2003','Can Tho','qtuong.257@gmail.com','0917895327','My note');
--INSERT INTO "Employees" ("fullname","accountID","imageURL","birthDate","address","email","phone","Notes") VALUES ('Pham Hieu Tho',3,'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/jqrn3v2u8qf3lpstudgn','07-04-2003','Can Tho','hieutho1510@gmail.com','0938973817','My note');
--INSERT INTO "Employees" ("fullname","accountID","imageURL","birthDate","address","email","phone","Notes") VALUES ('Pham Huu Nhan',4,'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/een5b4xmq5twvne8ce0c','07-25-2003','Can Tho','huunhan.service@gmail.com','0123456789','Off');
--INSERT INTO "Employees" ("fullname","accountID","imageURL","birthDate","address","email","phone","Notes") VALUES ('Ly Minh Nghia',5,'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/kubpbgbrjxtcuq9yg6b8','07-25-2003','Can Tho','minhnghia.service@gmail.com','0123456789','Off');
--GO
--ALTER TABLE "Employees" CHECK CONSTRAINT ALL
--go
--GO

---------------------------------------------
--set quoted_identifier on
--go
--ALTER TABLE "Customers" NOCHECK CONSTRAINT ALL
--go
--INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (6, 'Pham Hieu Tho', 'Can Tho', '0938973817', 'hieutho1510@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/jqrn3v2u8qf3lpstudgn', 'Pham Hieu Tho', '1234567890123456', '2023-01-01', '2025-12-31', 123, 'credit card', 100, 'Gold');
--INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (7, 'Ly Minh Nghia', 'Can Tho', '0123456781', 'minhnghia.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/kubpbgbrjxtcuq9yg6b8', 'Ly Minh Nghia', '2345678901234567', '2023-01-01', '2025-12-31', 456, 'credit card', 100, 'Gold');
--INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (8, 'Pham Huu Nhan', 'Can Tho', '0123456782', 'huunhan.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/kubpbgbrjxtcuq9yg6b8', 'Pham Huu Nhan', '3456789012345678', '2023-01-01', '2025-12-31', 789, 'credit card', 100, 'Gold');
--INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (9, 'Nguyen Minhh Tuan', 'Can Tho', '0123436789', 'minhtuan.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/kubpbgbrjxtcuq9yg6b8', 'Nguyen Minhh Tuan', '4567890123456789', '2023-01-01', '2025-12-31', 234, 'prepaid card', 100, 'Gold');
--INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (10, 'Nguyen Thi My Hao', 'Can Tho', '0123456789', 'myhao.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/jqrn3v2u8qf3lpstudgn', 'Nguyen Thi My Hao', '5678901234567890', '2023-01-01', '2025-12-31', 567, 'prepaid card', 100, 'Gold');
--INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (11, 'Ong Kim Thanh', 'Can Tho', '0123456759', 'kimthanh.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/kubpbgbrjxtcuq9yg6b8', 'Ong Kim Thanh', '6789012345678901', '2023-01-01', '2025-12-31', 890, 'prepaid card', 100, 'Gold');
--INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (12, 'Nguyen Trong Tri', 'Can Tho', '0123466789', 'trongtri.cus0@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/kubpbgbrjxtcuq9yg6b8', 'Nguyen Trong Tri', '7890123456789012', '2023-01-01', '2025-12-31', 345, 'debit card', 100, 'Gold');
--INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (13, 'Nguyen Tan Phat', 'Can Tho', '0123457789', 'tanphat.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/kubpbgbrjxtcuq9yg6b8', 'Nguyen Tan Phat', '8901234567890123', '2023-01-01', '2025-12-31', 678, 'debit card', 100, 'Gold');
--INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (14, 'Truong Ngoc Anh', 'Can Tho', '0123458789', 'ngocanh.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/jqrn3v2u8qf3lpstudgn', 'Truong Ngoc Anh', '9012345678901234', '2023-01-01', '2025-12-31', 901, 'debit card', 100, 'Gold');
--INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (15, 'Nguyen Thi Anh Thu', 'Can Tho', '0923456789', 'anhthu.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/jqrn3v2u8qf3lpstudgn', 'Nguyen Thi Anh Thu', '0123456789012345', '2023-01-01', '2025-12-31', 123, 'credit card', 100, 'Gold');
--INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (16, 'Pham Van Hien', 'Can Tho', '0123423789', 'vanhien.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/kubpbgbrjxtcuq9yg6b8', 'Pham Van Hien', '1234567890123456', '2023-01-01', '2025-12-31', 456, 'credit card', 100, 'Gold');
--INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (17, 'To Ngoc Lan', 'Can Tho', '0123456119', 'ngoclan.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/jqrn3v2u8qf3lpstudgn', 'To Ngoc Lan', '2345678901234567', '2023-01-01', '2025-12-31', 789, 'credit card', 100, 'Gold');
--INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (18, 'Truong Thi Ngoc Trang', 'Can Tho', '0123456789', 'ngoctrang.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/jqrn3v2u8qf3lpstudgn', 'Truong Thi Ngoc Trang', '3456789012345678', '2023-01-01', '2025-12-31', 234, 'prepaid card', 100, 'Gold');
--INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (19, 'Nguyen Ngoc Hien', 'Can Tho', '0123453189', 'ngochien.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/jqrn3v2u8qf3lpstudgn', 'Nguyen Ngoc Hien', '4567890123456789', '2023-01-01', '2025-12-31', 567, 'prepaid card', 100, 'Gold');
--INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (20, 'Nguyen Thanh Dat', 'Can Tho', '0125356789', 'thanhdat.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/kubpbgbrjxtcuq9yg6b8', 'Nguyen Thanh Dat', '5678901234567890', '2023-01-01', '2025-12-31', 890, 'prepaid card', 100, 'Gold');
--INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (21, 'Dang Minh Dat', 'Can Tho', '0123453589', 'minhdat.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/kubpbgbrjxtcuq9yg6b8', 'Dang Minh Dat', '6789012345678901', '2023-01-01', '2025-12-31', 345, 'debit card', 100, 'Gold');
--INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (22, 'Tran Thi Thu Thao', 'Can Tho', '0112456789', 'thuthao.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/jqrn3v2u8qf3lpstudgn', 'Tran Thi Thu Thao', '7890123456789012', '2023-01-01', '2025-12-31', 678, 'debit card', 100, 'Gold');
--INSERT INTO "Customers" ( "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "cardName", "cardNumber","valueFrom","expirationDate","cvvNumber","cardType","point", "rank"	) VALUES (23, 'Vuong Nguyen Kim Tuyen', 'Can Tho', '0753456789', 'kimtuyen.cus@gmail.com', '07-04-2003', 'https://res.cloudinary.com/dwjl02bji/image/upload/f_auto,q_auto/v1/user/avatars/jqrn3v2u8qf3lpstudgn', 'Vuong Nguyen Kim Tuyen', '8901234567890123', '2023-01-01', '2025-12-31', 901, 'debit card', 100, 'Gold');

--GO
--ALTER TABLE "Customers" CHECK CONSTRAINT ALL
--go
--GO

---------------------------------------------
--set quoted_identifier on
--go
--ALTER TABLE "Blogs" NOCHECK CONSTRAINT ALL
--go
--INSERT INTO "Blogs" ( employeeID, title, content) VALUES 
--(2, 'Pancake SanTa Beetroot Strawberry', 
--'<p style="text-align: center">
--        <img
--          src="https://www.lottemart.vn/media/wysiwyg/pancake.jpg"
--          alt="pancake"
--          width="1200"
--          height="1200"
--        />
--      </p>' +
--'<p>
--        <span >
--          Pancake is a poetic dish originating from France, with many flavors
--          and many different ways of preparation, so Pancake always has its own
--          appeal. Today we will go into the kitchen and try to make this
--          extremely unique Strawberry Beetroot Pancake. This will definitely be
--          a dessert that will increase happiness in a cozy evening gathering
--          with the family.
--        </span>
--      </p>' +
--'<p style="text-align: center">
--        <span >
--          <img
--            src="https://www.lottemart.vn/media/wysiwyg/pancake1.jpg"
--            alt="pancake"
--            width="1200"
--            height="1200"
--          />
--        </span>
--      </p>' +
--'<p style="text-align: left">
--        <span >&nbsp;</span>
--      </p>
--' +
--'<p><strong>Ingredient:</strong></p>' +
--'<ul>
--        <li aria-level="1" >
--          Strawberries: 20 fruits.
--        </li>
--        <li aria-level="1" >
--          Pancake flour: 300 grams.
--        </li>
--        <li aria-level="1" >Milk: 2/3 Cup.</li>
--        <li aria-level="1" >Butter: 1 tablespoon.</li>
--        <li aria-level="1" >Beetroot: 120 grams.</li>
--        <li aria-level="1" >Eggs: 1 egg.</li>
--        <li aria-level="1" >Fresh Cream: 1/2 cup.</li>
--      </ul>' +
--'<p>
--        <strong>Step 1:</strong>
--        <span >
--          Cut the beetroot into very small and thin slices, then mince them or
--          put them in a blender to puree. Cut strawberries into rolls, then wash
--          and drain.</span
--        >
--      </p>' +
--'<p style="text-align: center">
--        <span >
--          <img
--            src="https://www.lottemart.vn/media/wysiwyg/4e6bacfa-6ffb-49c0-bc61-ebdde354b99a.jpg"
--            alt="pancake"
--            width="1200"
--            height="1200"
--          />
--        </span>
--      </p>' +
--'<p>
--        <strong>Step 2:</strong>
--        <span >
--          Put the cake flour in a bowl, then add eggs, milk and beetroot puree
--          and mix with a whisk.</span
--        >
--      </p>' +
--'<p style="text-align: center">
--        <span >
--          <img
--            src="https://www.lottemart.vn/media/wysiwyg/pancake3.jpg"
--            alt="pancake"
--            width="1200"
--            height="1200"
--          />
--        </span>
--      </p>' +
--'<p>
--        <strong>Step 3:</strong>
--        <span >
--          Put the butter in the hot pan, when the butter melts, add the smooth
--          flour into the pan to the size you want. When the cake is done, take
--          it out onto a plate.</span
--        >
--      </p>' +
--' <p style="text-align: center">
--        <span >
--          <img
--            src="https://www.lottemart.vn/media/wysiwyg/pancake4.jpg"
--            alt="pancake"
--            width="1200"
--            height="1200"
--          />
--        </span>
--      </p>' +
--'<p>
--        <strong>Step 4:</strong>
--        <span >
--          Put the cream and sugar in a bowl and gently whip the cream, prepare
--          the pancakes in order: pancakes, then cream, add a layer of pancakes,
--          and a layer of cream, on top of strawberries.</span
--        >
--      </p>' +
--'<p style="text-align: center">
--        <span >
--          <img
--            src="https://www.lottemart.vn/media/wysiwyg/pancake5.jpg"
--            alt="pancake"
--            width="1200"
--            height="1200"
--          />
--        </span>
--      </p>' +
--'<p>
--        <span >
--          How to make Reindeer: Place marshmallows under the stick, pin
--          chocolate squares to the stick, use hard Marshmallow for eyes and
--          cookies for horns. Let''s shape the reindeer''s nose using red
--          chocolate.</span
--        >
--      </p>' +
--'<p style="text-align: center">
--        <span >
--          <img
--            src="https://www.lottemart.vn/media/wysiwyg/pancake6.jpg"
--            alt="pancake"
--            width="1200"
--            height="1200"
--          />
--        </span>
--      </p>' +
--'<p>
--        <strong>Step 5:</strong>
--        <span >
--          So Pancake Santa is done, a little tip for decoration, you can add
--          cream on top of the strawberries to make them look more like
--          Santa.</span
--        >
--      </p>' +
--'<p style="text-align: center">
--        <span >
--          <img
--            src="https://www.lottemart.vn/media/wysiwyg/pancake7_1.jpg"
--            alt="pancake"
--            width="1200"
--            height="1200"
--          />
--        </span>
--      </p>');
--	  ---------------------------------------------
--INSERT INTO "Blogs" (employeeID, title, content) VALUES 
--(4, 'Spicy Kim Chi Cold Noodles', 
--'<p style="text-align: center">
--        <img
--          src="https://www.lottemart.vn/media/wysiwyg/milanh.jpg"
--          alt="pancake"
--          width="1200"
--          height="1200"
--        />
--      </p>' +
--'<p>
--        <span >
--          Noodles are a dish that is easy to crave, especially on rainy days and
--          are also extremely easy to eat. That''s why noodles will be on the menu
--          today, we will introduce to you Korean spicy Kim Chi noodles, with
--          only a few basic ingredients but this noodle dish is still very
--          special with its steaming flavor. its own guide.
--        </span>
--      </p>' +
--'<p style="text-align: center">
--        <span >
--          <img
--            src="https://www.lottemart.vn/media/wysiwyg/milanh1.jpg"
--            alt="pancake"
--            width="1200"
--            height="1200"
--          />
--        </span>
--      </p>' +
--'<p style="text-align: left">
--        <span >&nbsp;</span>
--      </p>
--' +
--'<p><strong>Ingredient:</strong></p>' +
--'<ul>
--        <li aria-level="1" >
--          Kimchi: a little.
--        </li>
--        <li aria-level="1" >
--          Cucumber: 1/2 fruit
--        </li>
--        <li aria-level="1" >Sesame: 1 little</li>
--        <li aria-level="1" >Vermicelli: 200 grams</li>
--        <li aria-level="1" >Boiled eggs: 1 egg</li>
--      </ul>' +
--'<p><strong>Spice:</strong></p>' +
--'<ul>
--        <li aria-level="1" >
--          Pepper: 2 spoons.
--        </li>
--        <li aria-level="1" >
--          Oligo olive oil: 2 spoons.
--        </li>
--        <li aria-level="1" >Sugar: 1 tablespoon.</li>
--        <li aria-level="1" >Sesame oil: 1 tablespoon.</li>
--        <li aria-level="1" >Vinegar: 2 tablespoons.</li>
--		<li aria-level="1" >Soy sauce: 1 tablespoon.</li>
--		<li aria-level="1" >Pepper: a little.</li>
--      </ul>'+
--'<p>
--        <strong>Step 1:</strong>
--        <span >
--          Cut Kim Chi, cut cucumber into pieces, mix with prepared spices. Cut
--          boiled eggs in half.</span
--        >
--      </p>' +
--'<p style="text-align: center">
--        <span >
--          <img
--            src="https://www.lottemart.vn/media/wysiwyg/milanh2.jpg"
--            alt="pancake"
--            width="1200"
--            height="1200"
--          />
--        </span>
--      </p>' +
--'<p>
--        <strong>Step 2:</strong>
--        <span >
--          Put the noodles in the heated water and use chopsticks to stir so the
--          noodles don''t stick together. When the water boils, add half a glass
--          of cold water and continue to add water 3-4 times.</span
--        >
--      </p>' +
--'<p style="text-align: center">
--        <span >
--          <img
--            src="https://www.lottemart.vn/media/wysiwyg/milanh3.jpg"
--            alt="pancake"
--            width="1200"
--            height="1200"
--          />
--        </span>
--      </p>' +
--'<p style="text-align: center">
--        <span >
--          <img
--            src="https://www.lottemart.vn/media/wysiwyg/milanh4.jpg" 
--            alt="pancake"
--            width="1200"
--            height="1200"
--          />
--        </span>
--      </p>' +
--'<p>
--        <strong>Step 3:</strong>
--        <span >
--          Take the noodles out, wash them to remove the boiled noodles, and add
--          ice water to make the noodles more chewy.</span
--        >
--      </p>' +
--' <p style="text-align: center">
--        <span >
--          <img
--            src="https://www.lottemart.vn/media/wysiwyg/milanh5.jpg"
--            alt="pancake"
--            width="1200"
--            height="1200"
--          />
--        </span>
--      </p>' +
--'<p>
--        <strong>Step 4:</strong>
--        <span >
--          Put the noodles in a bowl, add the kimchi with sauce to the noodles
--          and mix well.</span
--        >
--      </p>' +
--'<p style="text-align: center">
--        <span >
--          <img
--            src="https://www.lottemart.vn/media/wysiwyg/milanh6.jpg"
--            alt="pancake"
--            width="1200"
--            height="1200"
--          />
--        </span>
--      </p>' +
--'<p>
--        <strong>Step 5:</strong>
--        <span >
--          Add eggs, cucumber and sprinkle some sesame seeds. And let''s sip
--          together.</span
--        >
--      </p>' +
--'<p style="text-align: center">
--        <span >
--          <img
--            src="https://www.lottemart.vn/media/wysiwyg/milanh7.jpg" 
--            alt="pancake"
--            width="1200"
--            height="1200"
--          />
--        </span>
--      </p>'+
--'<p style="text-align: center">
--        <span >
--          <img
--            src="https://www.lottemart.vn/media/wysiwyg/milanh8.jpg"
--            alt="pancake"
--            width="1200"
--            height="1200"
--          />
--        </span>
--      </p>');

--GO
--ALTER TABLE "Blogs" CHECK CONSTRAINT ALL
--go
--GO

--------------------------------------
--set quoted_identifier on
--go
--ALTER TABLE "Feedbacks" NOCHECK CONSTRAINT ALL
--go
--INSERT INTO "Feedbacks" (customerID, title, content, date, rate) VALUES (1,'Great Service!', 'I had a wonderful experience with your service.', '2023-01-15', 5);
--INSERT INTO "Feedbacks" (customerID, title, content, date, rate) VALUES (2, 'Product Feedback', 'The product exceeded my expectations.', '2023-01-18', 4);
--INSERT INTO "Feedbacks" (customerID, title, content, date, rate) VALUES (3, 'Improvement Suggestion', 'I think there are areas that could be improved.', '2023-01-20', 3);
--INSERT INTO "Feedbacks" (customerID, title, content, date, rate) VALUES (4, 'Excellent Support', 'Your customer support team is fantastic!', '2023-01-22', 5);
--INSERT INTO "Feedbacks" (customerID, title, content, date, rate) VALUES (5, 'Quality Issues', 'I encountered some quality issues with the product.', '2023-01-25', 2);
--INSERT INTO "Feedbacks" (customerID, title, content, date, rate) VALUES (6, 'Prompt Delivery', 'The delivery was quick and on time.', '2023-01-28', 5);
--INSERT INTO "Feedbacks" (customerID, title, content, date, rate) VALUES (7, 'Customer Service Feedback', 'I had a pleasant experience with your customer service.', '2023-02-01', 4);
--INSERT INTO "Feedbacks" (customerID, title, content, date, rate) VALUES (8, 'Product Suggestions', 'Here are some suggestions for product enhancements.', '2023-02-05', 3);

--GO
--ALTER TABLE "Feedbacks" CHECK CONSTRAINT ALL
--go
--GO

--------------------------------------
set quoted_identifier on
go
ALTER TABLE "Suppliers" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Suppliers" (companyName, contactName, contactTitle, address, city, postalCode, phone, fax, homePage, description) VALUES ('GO! VIETNAM', 'John Doe', 'Manager', 'Ninh Kieu', 'Can Tho', '900000', '555-1234', '555-5678', 'https://go-vietnam.vn/', 'Supplier Description');
INSERT INTO "Suppliers" (companyName, contactName, contactTitle, address, city, postalCode, phone, fax, homePage, description) VALUES ('WinCommerce General Trading Services Joint Stock Company', 'Jane Smith', 'Sales Representative', 'Binh Thuy', 'Can Tho', '900000', '67890', '555-9876', 'https://winmart.vn/', 'Supplier Description');
INSERT INTO "Suppliers" (companyName, contactName, contactTitle, address, city, postalCode, phone, fax, homePage, description) VALUES ('The CocaCola Company', 'Bob Johnson', 'Owner', 'Thot Not', 'Can Tho', '900000', '555-1111', '555-2222', 'https://www.coca-cola.com/vn/vi', 'Supplier Description');
INSERT INTO "Suppliers" (companyName, contactName, contactTitle, address, city, postalCode, phone, fax, homePage, description) VALUES ('Bach Hoa Xanh Trading Joint Stock Company', 'Alice Johnson', 'Sales Manager', 'Cai Rang', 'Can Tho', '900000', '555-3333', '555-4444', 'https://www.bachhoaxanh.com/', 'Supplier Description');
INSERT INTO "Suppliers" (companyName, contactName, contactTitle, address, city, postalCode, phone, fax, homePage, description) VALUES ('LOTTE VIETNAM SHOPPING JOINT STOCK COMPANY', 'Charlie Brown', 'Marketing Coordinator', 'O Mon', 'Can Tho', '900000', '555-5555', '555-6666', 'https://www.lottemart.vn/', 'Supplier Description');

GO
ALTER TABLE "Suppliers" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
ALTER TABLE "Brand" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Brand" ( supplierID ,brandName, address) VALUES (1,'Toshiba', 'Can Tho');
INSERT INTO "Brand" ( supplierID ,brandName, address) VALUES (2, 'Mitsubishi', 'Can Tho');
INSERT INTO "Brand" ( supplierID ,brandName, address) VALUES (3, 'NowFood', 'Can Tho');
INSERT INTO "Brand" ( supplierID ,brandName, address) VALUES (4, 'Green Air', 'Can Tho');
INSERT INTO "Brand" ( supplierID ,brandName, address) VALUES (5, 'Golden Health', 'Can Tho');
INSERT INTO "Brand" ( supplierID ,brandName, address) VALUES (5, 'Juice', 'Can Tho');


GO
ALTER TABLE "Brand" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
ALTER TABLE "Branch" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Branch" ( companyName, branchName, address, city, phone, fax, postalCode, description) VALUES ('ZipMart co.', 'ZipMart Ninh Kieu', 'Ninh Kieu', 'Can Tho', '555-1111', '555-2222', '900000', 'Ninh Kieu Branch');
INSERT INTO "Branch" ( companyName, branchName, address, city, phone, fax, postalCode, description) VALUES ('ZipMart co.', 'ZipMart Binh Thuy', 'Ninh Kieu', 'Can Tho', '555-1111', '555-2222', '900000', 'Ninh Kieu Branch');
INSERT INTO "Branch" ( companyName, branchName, address, city, phone, fax, postalCode, description) VALUES ('ZipMart co.', 'ZipMart Cai Rang', 'Ninh Kieu', 'Can Tho', '555-1111', '555-2222', '900000', 'Ninh Kieu Branch');
INSERT INTO "Branch" ( companyName, branchName, address, city, phone, fax, postalCode, description) VALUES ('ZipMart co.', 'ZipMart Phong Dien', 'Ninh Kieu', 'Can Tho', '555-1111', '555-2222', '900000', 'Ninh Kieu Branch');

GO
ALTER TABLE "Branch" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
ALTER TABLE "Categories" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Categories" ("CategoryName", "Description", "restockThreshold", "newAdjustment") VALUES ('Household Appliances', 'Home electronic devices', 500,0);
INSERT INTO "Categories" ("CategoryName", "Description", "restockThreshold", "newAdjustment") VALUES ('Spices', 'Various cooking spices', 500,0);
INSERT INTO "Categories" ("CategoryName", "Description", "restockThreshold", "newAdjustment") VALUES ('Meat', 'Various meat products', 500,0);
INSERT INTO "Categories" ("CategoryName", "Description", "restockThreshold", "newAdjustment") VALUES ('Seafood', 'Various seafood products', 500,0);
INSERT INTO "Categories" ("CategoryName", "Description", "restockThreshold", "newAdjustment") VALUES ('Vegetables', 'Fresh vegetables', 500,0);
INSERT INTO "Categories" ("CategoryName", "Description", "restockThreshold", "newAdjustment") VALUES ('Convenience Products', 'Convenient and ready-to-use products', 500,0);
INSERT INTO "Categories" ("CategoryName", "Description", "restockThreshold", "newAdjustment") VALUES ('Fashion', 'Clothing and accessories', 500,0);
INSERT INTO "Categories" ("CategoryName", "Description", "restockThreshold", "newAdjustment") VALUES ('Fruits', 'Fresh fruits', 500,0);
GO
ALTER TABLE "Categories" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
ALTER TABLE "ImportOrder" NOCHECK CONSTRAINT ALL
go
INSERT INTO dbo.ImportOrder (supplierID, categoryID, branchID, companyName, dateImport, hsCode, nameProduct, amountDelivery, address, city, phone, fax, postalCode, description, status, leadtime)
VALUES (1, 1, 1, 'ZipMart co.', '2023-01-15', 85166090, 'Kitchen infrared', 100, 'Ninh Kieu', 'Can Tho', '555-1111', '555-2222', '900000', 'Import of Kitchen infrared', 'Pending', 4);
INSERT INTO dbo.ImportOrder (supplierID, categoryID, branchID, companyName, dateImport, hsCode, nameProduct, amountDelivery, address, city, phone, fax, postalCode, description, status, leadtime)
VALUES (1, 2, 6, 'ZipMart co.', '2023-01-15', 73239390, 'Stainless Steel Pot', 30, 'Ninh Kieu', 'Can Tho', '555-4321', '555-2334', '900000', 'Import of Stainless Steel Pot', 'In Progress', 2);
INSERT INTO dbo.ImportOrder (supplierID, categoryID, branchID, companyName, dateImport, hsCode, nameProduct, amountDelivery, address, city, phone, fax, postalCode, description, status, leadtime)
VALUES (1, 3, 1, 'ZipMart co.', '2023-01-15', 85166090, 'oil-free fryer', 50, 'Ninh Kieu', 'Can Tho', '555-2123', '555-4442', '900000', 'Import of Kitchen infrared', 'Pending', 6);
INSERT INTO dbo.ImportOrder (supplierID, categoryID, branchID, companyName, dateImport, hsCode, nameProduct, amountDelivery, address, city, phone, fax, postalCode, description, status, leadtime)
VALUES (1, 1, 6, 'ZipMart co.', '2023-01-15', 70133700, 'Glass Bowl', 90, 'Ninh Kieu', 'Can Tho', '555-6123', '555-5213', '900000', 'Import of Glass Bowl', 'Completed', 4);

GO
ALTER TABLE "ImportOrder" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
ALTER TABLE "InventoryStatus" NOCHECK CONSTRAINT ALL
go
INSERT INTO dbo.InventoryStatus (statusName) VALUES ('INSTOCK');
INSERT INTO dbo.InventoryStatus (statusName) VALUES ('LOWSTOCK');
INSERT INTO dbo.InventoryStatus (statusName) VALUES ('OUTOFSTOCK');

GO
ALTER TABLE "InventoryStatus" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
ALTER TABLE "Products" NOCHECK CONSTRAINT ALL
go
INSERT INTO dbo.Products (categoryID, brandID, supplierID, productName, imageURL, unitPrice, quantity, quantityInStock, unit, sortdescription ,description, viewCount, discount, avaliable, inventoryStatus)
VALUES (1, 1, 1, 'Iron', 'images\products\homeelectric\banuikho.jpg', 15.0000, 50, 150, 'unit',
'<p>
	<span >Goldsun Gir2202 Dry Iron will be an indispensable device in the family, considered a modern iron, helping to iron clothes quickly, saving time and effort for the user.</span>
</p>' +
'<p>
	<span >Outstanding features: Anti-slip handle design. The table top is covered with high quality non-stick Teflon. Remove wrinkles from all fabrics. 3 levels of temperature adjustment. Automatically turns off power when not in use.</span>
</p>' +
'<p>
	<span >Technical specifications: Power: 1000W, 3 temperature adjustment levels, 3 ironing temperature levels. Surface covered with Teflon non-stick coating. Power source 220V~50HZ. Weight: 0.6kg</span>
</p>' + 
'<p>
	<span >Note when using: Low temperature for synthetic fibers/silk. Average temperature used for fabrics. High temperature for linen and cotton. Place of production: Vietnam.</span>
</p>', 

'<p>
	<span >Goldsun Gir2202 Dry Iron will be an indispensable device in the family, considered a modern iron, helping to iron clothes quickly, saving time and effort for the user.</span>
</p>' +
'<p>
	<span >Outstanding features: Anti-slip handle design. The table top is covered with high quality non-stick Teflon. Remove wrinkles from all fabrics. 3 levels of temperature adjustment. Automatically turns off power when not in use.</span>
</p>' +
'<p>
	<span >Technical specifications: Power: 1000W, 3 temperature adjustment levels, 3 ironing temperature levels. Surface covered with Teflon non-stick coating. Power source 220V~50HZ. Weight: 0.6kg</span>
</p>' + 
'<p>
	<span >Note when using: Low temperature for synthetic fibers/silk. Average temperature used for fabrics. High temperature for linen and cotton. Place of production: Vietnam.</span>
</p>', 200, 15, 1, 1);

GO

ALTER TABLE "Products" CHECK CONSTRAINT ALL
go
GO

------------------------------------
set quoted_identifier on
go
ALTER TABLE "ThresholdAdjustment" NOCHECK CONSTRAINT ALL
go
INSERT INTO "ThresholdAdjustment" ( categoryID, reasonAdjustment, new_restockThreshold, dateAdjusted, statusThresholdAdjustments) VALUES (1, 'Low Inventory', 700, '2023-01-15 08:30:00', 'Accept');
INSERT INTO "ThresholdAdjustment" ( categoryID, reasonAdjustment, new_restockThreshold, dateAdjusted, statusThresholdAdjustments) VALUES (2, 'Excess Inventory', 500, '2023-02-20 10:45:00', 'Pending');
GO
ALTER TABLE "ThresholdAdjustment" CHECK CONSTRAINT ALL
go
GO

--------------------------------------
--set quoted_identifier on
--go
--ALTER TABLE "Orders" NOCHECK CONSTRAINT ALL
--go
--INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (1, 1, '2023-01-15 08:30:00', '2023-01-18 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
--INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (2, 1, '2023-01-15 08:30:00', '2023-01-18 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');
--INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (3, 2, '2023-01-16 09:30:00', '2023-01-18 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');
--INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (4, 2, '2023-01-16 09:30:00', '2023-01-18 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');
-----------------------------------
--INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (5, 3, '2023-01-17 10:30:00', '2023-01-20 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Pending');
--INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (6, 3, '2023-01-17 10:30:00', '2023-01-20 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Pending');
--INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (7, 4, '2023-01-18 11:00:00', '2023-01-20 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Pending');
--INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (8, 4, '2023-01-18 11:00:00', '2023-01-20 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
--INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (9, 2, '2023-01-19 11:00:00', '2023-01-23 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Pending');
--------------------------------
--INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (10, 2, '2023-01-19 11:00:00', '2023-01-23 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
--INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (11, 1, '2023-01-20 15:30:00', '2023-01-23 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
--INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (11, 1, '2023-01-20 15:30:00', '2023-01-23 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Pending');
--INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (12, 3, '2023-01-20 15:30:00', '2023-01-23 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
--INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (13, 3, '2023-01-20 18:30:00', '2023-01-23 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');
--INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (14, 4, '2023-01-21 08:30:00', '2023-01-23 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
--INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (15, 4, '2023-01-21 08:30:00', '2023-01-23 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');
--------------------------------
--INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (16, 3, '2023-01-22 08:30:00', '2023-01-25 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
--INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (17, 3, '2023-01-22 15:30:00', '2023-01-25 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
--INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (17, 2, '2023-01-23 18:30:00', '2023-01-25 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');
--INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (18, 2, '2023-01-24 08:30:00', '2023-01-30 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');
--INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (1, 4, '2023-01-25 18:30:00', '2023-01-30 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
-------------------------------
--INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (2, 4, '2023-01-26 08:30:00', '2023-01-30 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
--INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (2, 1, '2023-01-27 15:30:00', '2023-01-30 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');
--INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (3, 1, '2023-01-28 08:30:00', '2023-01-30 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
--INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (4, 1, '2023-01-29 18:30:00', '2023-02-03 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Shipped');
--INSERT INTO "Orders" (customerID, employeeID, orderDate, shipDate, shipAddress, note, status) VALUES (2, 1, '2023-01-30 15:30:00', '2023-02-03 12:45:00', 'Ninh Kieu, Can Tho', 'Special instructions for the order.', 'Processing');

--GO
--ALTER TABLE "Orders" CHECK CONSTRAINT ALL
--go
--GO

--------------------------------------
--set quoted_identifier on
--go
--ALTER TABLE "OrdersDetails" NOCHECK CONSTRAINT ALL
--go
--INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (1, 1, 15.0000, 1, 15, 12.7500, 'credit card', 'Pham Hieu Tho', '1234567890123456');
--INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (2, 2, 8.4300, 2, 15, 14.3300, 'credit card', 'Ly Minh Nghia', '2345678901234567');
--INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (3, 3, 7.1500, 1, 27, 5.2200, 'credit card', 'Pham Huu Nhan', '3456789012345678');
--INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (4, 4, 15.0000, 3, 0, 45.0000, 'prepaid card', 'Nguyen Minhh Tuan', '4567890123456789');
--INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (5, 5, 0.5300, 1, 0, 0.5300, 'prepaid card', 'Nguyen Thi My Hao', '5678901234567890');
----------------------------
--INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (6, 6, 2.2100, 1, 0, 2.2100, 'prepaid card', 'Ong Kim Thanh', '6789012345678901');
--INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (7, 7, 0.6900, 1, 0,  0.6900, 'debit card', 'Nguyen Trong Tri', '7890123456789012');
--INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (8, 8, 1.5600, 3, 0, 4.6800, 'debit card', 'Nguyen Tan Phat', '8901234567890123');
--INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (9, 9, 0.2200, 3, 0,  0.6600, 'debit card', 'Truong Ngoc Anh', '9012345678901234');
--INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (10, 1, 15.0000, 1, 15, 12.7500, 'credit card', 'Nguyen Thi Anh Thu', '0123456789012345');
--INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (11, 10, 0.2300, 1, 0, 0.2300, 'credit card', 'Pham Van Hien', '1234567890123456');
--------------------------------
--INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (12, 1, 15.0000, 1, 15, 12.7500, 'credit card', 'Pham Van Hien', '1234567890123456');
--INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (13, 1, 15.0000, 5, 15, 63.7500,  'credit card', 'To Ngoc Lan', '2345678901234567');
--INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (14, 20, 2.3100, 1, 10, 2.0800, 'prepaid card', 'Truong Thi Ngoc Trang', '3456789012345678');
--INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (15, 21, 2.3100, 1, 15, 1.9600, 'prepaid card', 'Nguyen Ngoc Hien', '4567890123456789');
--INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (16, 21, 2.3100, 1, 15, 1.9600, 'prepaid card', 'Nguyen Thanh Dat', '5678901234567890');
--------------------------------
--INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (17, 21, 2.3100, 1, 15, 2.0800, 'debit card', 'Dang Minh Dat', '6789012345678901');
--INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (18, 22, 4.3200, 3, 0, 12.9600, 'debit card', 'Tran Thi Thu Thao', '7890123456789012');
--INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (19, 23, 2.1100, 3, 25, 4.7500, 'debit card', 'Tran Thi Thu Thao', '7890123456789012');
--INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (20, 24, 1.8900, 3, 5, 5.3900, 'debit card', 'Vuong Nguyen Kim Tuyen', '8901234567890123');
--INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (21, 25, 3.4200, 3, 5, 9.7500, 'credit card', 'Pham Hieu Tho', '1234567890123456');
-------------------------------
--INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (22, 30, 5.3200, 1, 20, 4.2600, 'credit card', 'Ly Minh Nghia', '2345678901234567');
--INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (23, 30, 5.3200, 1, 20, 4.2600, 'credit card', 'Ly Minh Nghia', '2345678901234567');
--INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (24, 30, 5.3200, 1, 20, 4.2600, 'credit card', 'Pham Huu Nhan', '3456789012345678');
--INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (25, 30, 5.3200, 1, 20, 4.2600, 'prepaid card', 'Nguyen Minhh Tuan', '4567890123456789');
--INSERT INTO "OrdersDetails" ( orderID, productID, unitPrice, quantity, discount, totalPrice, paymentMethod, cardName, cardNumber) VALUES (26, 30, 5.3200, 1, 20, 4.2600, 'prepaid card', 'Nguyen Thi My Hao', '5678901234567890');

--GO
--ALTER TABLE "OrdersDetails" CHECK CONSTRAINT ALL
--go
--GO

-------------------------------------

GO
USE [master]
GO
ALTER DATABASE [Cp2396g01_group5_db] SET  READ_WRITE 
GO
