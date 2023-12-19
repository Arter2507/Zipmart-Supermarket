-- Enable the NOCOUNT option to prevent the return of the count of affected rows by DML statements.
SET NOCOUNT ON;
GO

-- Use the 'master' database to perform system management operations.
USE [master];
GO

-- Check IF the 'ZipMart' database exists, and drop it IF it does.
IF EXISTS (SELECT * FROM sys.databases WHERE name='ZipMart')
BEGIN
    DROP DATABASE [ZipMart];
END
GO

-- Get the directory path of the master data file to use for the new 'ZipMart' data and log files.
DECLARE @device_directory NVARCHAR(520);
SELECT @device_directory = SUBSTRING(physical_name, 1, CHARINDEX(N'master.mdf', LOWER(physical_name)) - 1)
FROM master.sys.master_files WHERE database_id = 1 AND file_id = 1;

-- Create the 'ZipMart' database with primary data file and log file locations.
EXECUTE ('CREATE DATABASE ZipMart
  ON PRIMARY (NAME = N''ZipMart'', FILENAME = N''' + @device_directory + N'zmart.mdf'')
  LOG ON (NAME = N''ZipMart_log'',  FILENAME = N''' + @device_directory + N'zmart.ldf'')');
GO

-- Set options for the 'ZipMart' database depending on the SQL Server version.
IF CAST(SERVERPROPERTY('ProductMajorVersion') AS INT) < 12 
BEGIN
  -- For versions earlier than SQL Server 2014
  ALTER DATABASE [ZipMart] SET RECOVERY SIMPLE WITH NO_WAIT;
  ALTER DATABASE [ZipMart] SET AUTO_UPDATE_STATISTICS_ASYNC OFF;
END
ELSE 
BEGIN
  -- For SQL Server 2014 and later
  ALTER DATABASE [ZipMart] SET RECOVERY SIMPLE WITH NO_WAIT;
  ALTER DATABASE [ZipMart] SET AUTO_UPDATE_STATISTICS_ASYNC OFF;
END
GO

-- Set quoted identIFy for database
SET quoted_identIFier ON
GO

-- Set the date format to month/day/year.
SET DATEFORMAT mdy;
GO

-- Set the compatibility level for the 'ZipMart' database.
ALTER DATABASE [ZipMart] SET COMPATIBILITY_LEVEL = 150;
GO

-- IF Full-Text Indexing is installed, enable it for the 'ZipMart' database.
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
BEGIN
  EXEC [ZipMart].[dbo].[sp_fulltext_database] @action = 'enable';
END
GO

-- Set ANSI and ARITHABORT options for the 'ZipMart' database.
ALTER DATABASE [ZipMart] SET ANSI_NULL_DEFAULT OFF;
ALTER DATABASE [ZipMart] SET ANSI_NULLS OFF;
ALTER DATABASE [ZipMart] SET ANSI_PADDING OFF;
ALTER DATABASE [ZipMart] SET ANSI_WARNINGS OFF;
ALTER DATABASE [ZipMart] SET ARITHABORT OFF;
GO

-- Set various options for the 'ZipMart' database.
-- (ALTER DATABASE statements are alphabetically ordered for clarity)
ALTER DATABASE [ZipMart] SET ALLOW_SNAPSHOT_ISOLATION OFF;
ALTER DATABASE [ZipMart] SET AUTO_CLOSE OFF;
ALTER DATABASE [ZipMart] SET AUTO_SHRINK OFF;
ALTER DATABASE [ZipMart] SET AUTO_UPDATE_STATISTICS ON;
ALTER DATABASE [ZipMart] SET DATE_CORRELATION_OPTIMIZATION OFF;
ALTER DATABASE [ZipMart] SET DB_CHAINING OFF;
ALTER DATABASE [ZipMart] SET DELAYED_DURABILITY = DISABLED;
ALTER DATABASE [ZipMart] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF );
ALTER DATABASE [ZipMart] SET HONOR_BROKER_PRIORITY OFF;
ALTER DATABASE [ZipMart] SET MULTI_USER;
ALTER DATABASE [ZipMart] SET NUMERIC_ROUNDABORT OFF;
ALTER DATABASE [ZipMart] SET PAGE_VERIFY CHECKSUM;
ALTER DATABASE [ZipMart] SET PARAMETERIZATION SIMPLE;
ALTER DATABASE [ZipMart] SET QUOTED_IDENTIFIER OFF;
ALTER DATABASE [ZipMart] SET QUERY_STORE = OFF;
ALTER DATABASE [ZipMart] SET READ_COMMITTED_SNAPSHOT OFF;
ALTER DATABASE [ZipMart] SET RECURSIVE_TRIGGERS OFF;
ALTER DATABASE [ZipMart] SET TARGET_RECOVERY_TIME = 60 SECONDS;
ALTER DATABASE [ZipMart] SET TRUSTWORTHY OFF;
GO

-- Switch to the 'ZipMart' database to perform subsequent commands.
USE [ZipMart];
GO

IF exists (select * from sysobjects where id = object_id('dbo.Sales by Year') and sysstat & 0xf = 4)
	drop procedure [dbo].[Sales by Year]
GO
IF exists (select * from sysobjects where id = object_id('dbo.UpdateTotalProductQuantities') and sysstat & 0xf = 4)
	drop procedure [dbo].[UpdateTotalProductQuantities]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Ten Most Expensive Products') and sysstat & 0xf = 4)
	drop procedure [dbo].[Ten Most Expensive Products]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Ten Best Seller Products') and sysstat & 0xf = 4)
	drop procedure [dbo].[Ten Best Seller Products]
GO
IF exists (select * from sysobjects where id = object_id('dbo.OrderByQuarter') and sysstat & 0xf = 2)
	drop view [dbo].[OrderByQuarter]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Sales Totals by Amount') and sysstat & 0xf = 2)
	drop view [dbo].[Sales Totals by Amount]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Summary of Sales by Quarter') and sysstat & 0xf = 2)
	drop view [dbo].[Summary of Sales by Quarter]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Summary of Sales by Year') and sysstat & 0xf = 2)
	drop view [dbo].[Summary of Sales by Year]
GO
IF exists (select * from sysobjects where id = object_id('dbo.InvoiceView') and sysstat & 0xf = 2)
	drop view [dbo].[InvoiceView]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Order Subtotals') and sysstat & 0xf = 2)
	drop view [dbo].[Order Subtotals]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Orders Qry') and sysstat & 0xf = 2)
	drop view [dbo].[Orders Qry]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Products Above Average Price') and sysstat & 0xf = 2)
	drop view [dbo].[Products Above Average Price]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Products by Category') and sysstat & 0xf = 2)
	drop view [dbo].[Products by Category]
GO
IF exists (select * from sysobjects where id = object_id('dbo.BlogFeedBacks') and sysstat & 0xf = 3)
	drop table [dbo].[BlogFeedBacks]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Blogs') and sysstat & 0xf = 3)
	drop table [dbo].[Blogs]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Branch') and sysstat & 0xf = 3)
	drop table [dbo].[Branch]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Import') and sysstat & 0xf = 3)
	drop table [dbo].[Import]
GO
IF exists (select * from sysobjects where id = object_id('dbo.ThresholdAdjustment') and sysstat & 0xf = 3)
	drop table [dbo].[ThresholdAdjustment]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Cart') and sysstat & 0xf = 3)
	drop table [dbo].[Cart]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Orders') and sysstat & 0xf = 3)
	drop table [dbo].[Orders]
GO
IF exists (select * from sysobjects where id = object_id('dbo.OrdersDetails') and sysstat & 0xf = 3)
	drop table [dbo].[OrdersDetails]
GO
IF exists (select * from sysobjects where id = object_id('dbo.SalesTarget') and sysstat & 0xf = 3)
	drop table [dbo].[SalesTarget]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Managers') and sysstat & 0xf = 3)
	drop table [dbo].[Managers]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Employees') and sysstat & 0xf = 3)
	drop table [dbo].[Employees]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Products') and sysstat & 0xf = 3)
	drop table [dbo].[Products]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Feedbacks') and sysstat & 0xf = 3)
	drop table [dbo].[Feedbacks]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Customers') and sysstat & 0xf = 3)
	drop table [dbo].[Customers]
GO
IF exists (select * from sysobjects where id = object_id('dbo.CreditCard') and sysstat & 0xf = 3)
	drop table [dbo].[CreditCard]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Accounts') and sysstat & 0xf = 3)
	drop table [dbo].[Accounts]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Permission') and sysstat & 0xf = 3)
	drop table [dbo].[Permission]
GO

IF exists (select * from sysobjects where id = object_id('dbo.Brand') and sysstat & 0xf = 3)
	drop table [dbo].[Brand]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Suppliers') and sysstat & 0xf = 3)
	drop table [dbo].[Suppliers]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Warehouse') and sysstat & 0xf = 3)
	drop table [dbo].[Warehouse]
GO
IF exists (select * from sysobjects where id = object_id('dbo.Categories') and sysstat & 0xf = 3)
	drop table [dbo].[Categories]
GO
IF exists (select * from sysobjects where id = object_id('dbo.InventoryStatus') and sysstat & 0xf = 3)
	drop table [dbo].[InventoryStatus]
GO



SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create table PERMISSION--
CREATE TABLE [dbo].[Permission](
	[permissionID] [int] IDENTITY(1,1) NOT NULL,
	[permissionName] [nvarchar](50) NULL,
	CONSTRAINT "PK_Permission" PRIMARY KEY  CLUSTERED 
	(
		"permissionID"ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
)ON [Primary]

GO
 CREATE  INDEX "PermissionName" ON "dbo"."Permission"("permissionName") 
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table CreditCard--
CREATE TABLE [dbo].[CreditCard](
	[cardID] [int] IDENTITY(1,1) NOT NULL,
	[cardName] [nvarchar](255) NULL,
	[cardNumber] [varchar](16) NULL,
	[valueFrom] [datetime] NULL,
	[expirationDate] [dateTime] NULL,
	[cvvNumber] [int] NULL,
	[cardType] [nvarchar](20) NULL,
	CONSTRAINT "PK_CreditCard" PRIMARY KEY CLUSTERED
	(
	"cardID"ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
	CONSTRAINT "CK_cvvNumber" CHECK (LEN(cvvNumber) = 3 AND cvvNumber LIKE '[0-9][0-9][0-9]'),
	CONSTRAINT [CK_CardNumberFormat] CHECK (LEN(cardNumber) = 16 AND cardNumber LIKE '[0-9][0-9][0-9][0-9] [0-9][0-9][0-9][0-9] [0-9][0-9][0-9][0-9] [0-9][0-9][0-9][0-9]')
)ON [Primary]

GO 
 CREATE INDEX "CardNumber" ON "dbo"."CreditCard"("cardNumber")
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Account--
CREATE TABLE [dbo].[Accounts](
	[accountID] [int] IDENTITY(1,1) NOT NULL,
	[userName] [nvarchar](50) NULL,
	[password] [nvarchar](50) NULL,
	[hashedPassword] [VARBINARY](MAX),
	[permissionID] [int] NULL,
	[description] [nvarchar](255) NULL,
	CONSTRAINT "PK_Accounts" PRIMARY KEY  CLUSTERED 
	(
		"accountID"ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Accounts_Permission" FOREIGN KEY 
	(
		"permissionID"
	) REFERENCES "dbo"."Permission" (
		"permissionID"
	),
)ON [Primary]

GO
 CREATE INDEX "Username" ON "dbo"."Accounts"("userName")
GO
 CREATE INDEX "Password" ON "dbo"."Accounts"("password")
GO
 CREATE INDEX "PermissionID" ON "dbo"."Accounts"("permissionID")
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Manager--
CREATE TABLE [dbo].[Managers](
	[managerID] [int] IDENTITY(1,1) NOT NULL,
	[accountID] [int] NOT NULL,
	[fullname] [nvarchar](50) NULL,
	[address] [nvarchar](255) NULL,
	[phone] [nvarchar](50) NULL,
	[email] [nvarchar](255) NULL,
	CONSTRAINT "PK_Managers" PRIMARY KEY  CLUSTERED 
	(
		"managerID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Manager_Accounts" FOREIGN KEY 
	(
		"accountID"
	) REFERENCES "dbo"."Accounts" (
		"accountID"
	),
)ON [Primary]

GO
 CREATE INDEX "FullName" ON "dbo"."Managers"("fullname")
GO
 CREATE INDEX "Address" ON "dbo"."Managers"("address")
GO
 CREATE INDEX "Email" ON "dbo"."Managers"("email")
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Employee--
CREATE TABLE [dbo].[Employees](
	[employeeID] [int] IDENTITY(1,1) NOT NULL,
	[accountID] [int] NOT NULL,
	[fullname] [nvarchar](50) NULL,
	[address] [nvarchar](255) NULL,
	[phone] [nvarchar](50) NULL,
	[email] [nvarchar](255) NULL,
	[birthDate] [datetime] NULL ,
	[Notes] [nvarchar](max) NULL,
	imageURL [nvarchar](255) NULL,

	CONSTRAINT "PK_Employee" PRIMARY KEY CLUSTERED
	(
	"employeeID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Employee_Account" FOREIGN KEY 
	(
	"accountID"
	) REFERENCES "dbo"."Accounts"
	(
	"accountID"
	),
	CONSTRAINT "CK_Birthdate" CHECK (birthDate < getdate())
)ON [Primary]

GO
 CREATE INDEX "FullName" ON "dbo"."Employees"("fullname")
GO
 CREATE INDEX "Address" ON "dbo"."Employees"("address")
GO
 CREATE INDEX "Email" ON "dbo"."Employees"("email")
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Customers--
CREATE TABLE [dbo].[Customers](
	[customerID] [int] IDENTITY(1,1) NOT NULL,
	[cardID] [int] NOT NULL,
	[accountID] [int] NULL,
	[fullname] [nvarchar](255) NULL,
	[address] [nvarchar](50) NULL,
	[phone] [nvarchar](50) NULL,
	[email] [nvarchar](255) NULL,
	[birthDate] [datetime] NULL ,
	imageURL [nvarchar](255) NULL,
	[point] [bigint] NULL,
	[rank] [nvarchar](50) NULL,
	CONSTRAINT "PK_Customer" PRIMARY KEY CLUSTERED
	(
	"customerID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Customer_CreditCard" FOREIGN KEY 
	(
	"cardID"
	) REFERENCES "dbo"."CreditCard"
	(
	"cardID"
	),
	CONSTRAINT "FK_Customer_Account" FOREIGN KEY 
	(
	"accountID"
	) REFERENCES "dbo"."Accounts"
	(
	"accountID"
	),
	CONSTRAINT "CK_Birthdate_Customer" CHECK (birthDate < getdate())
)ON [Primary]

GO
 CREATE INDEX "FullName" ON "dbo"."Customers"("fullname")
GO
 CREATE INDEX "Address" ON "dbo"."Customers"("address")
GO
 CREATE INDEX "Email" ON "dbo"."Customers"("email")
GO
 CREATE INDEX "Rank" ON "dbo"."Customers"("rank")
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table blogFB--
CREATE TABLE [dbo].[BlogFeedBacks](
	[feedbackID] [int] IDENTITY(1,1) NOT NULL,
	[customerID] [int] NOT NULL,
	[title] [nvarchar](50) NULL,
	[content] [nvarchar](max) NULL,
	[date] [datetime] NULL,
	CONSTRAINT "PK_BlogFeedbacks" PRIMARY KEY  CLUSTERED 
	(
		"feedbackID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_BlogFeedBack_Customer" FOREIGN KEY 
	(
		"customerID"
	) REFERENCES "dbo"."Customers" (
		"customerID"
	),
)ON [Primary]

GO
 CREATE INDEX "Title" ON "dbo"."blogFeedBacks"("title")
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Blogs--
CREATE TABLE [dbo].[Blogs](
	[blogID] [int] IDENTITY(1,1) NOT NULL,
	[employeeID] [int] NOT NULL,
	[feedbackID] [int] NOT NULL,
	[title] [nvarchar](255) NULL,
	[imageURL] [nvarchar](255) NULL,
	[content] [nvarchar](max) NULL,
	[viewCount] [int] NULL,
	CONSTRAINT "PK_Feedback" PRIMARY KEY  CLUSTERED 
	(
		"blogID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Blog_Employee" FOREIGN KEY 
	(
		"employeeID"
	) REFERENCES "dbo"."Employees" (
		"employeeID"
	),
	CONSTRAINT "FK_Blog_BlogFeedBack" FOREIGN KEY 
	(
		"feedbackID"
	) REFERENCES "dbo"."blogFeedBacks" (
		"feedbackID"
	),
)ON [Primary]

GO
 CREATE INDEX "Title_Blog" ON "dbo"."Blogs"("title")
GO
 CREATE INDEX "ViewCount" ON "dbo"."Blogs"("viewCount")
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table FeedBacks--
CREATE TABLE [dbo].[Feedbacks](
	[feedbackID] [int] IDENTITY(1,1) NOT NULL,
	[customerID] [int] NOT NULL,
	[title] [nvarchar](255) NOT NULL,
	[content] [nvarchar](max) NULL,
	[date] [datetime] NULL,
	[rate] [int] NULL,
	CONSTRAINT "PK_Feedback_Cus" PRIMARY KEY  CLUSTERED 
	(
		"feedbackID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_FeedBack_Customer" FOREIGN KEY 
	(
		"customerID"
	) REFERENCES "dbo"."Customers" (
		"customerID"
	),
)ON [Primary]

GO
 GO
 CREATE INDEX "Title_Feedback" ON "dbo"."Feedbacks"("title")
GO
 CREATE INDEX "Rate" ON "dbo"."Feedbacks"("rate")
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Brand--
CREATE TABLE [dbo].[Brand](
	[brandID] [int] IDENTITY(1,1) NOT NULL,
	[brandName] [nvarchar](255) NOT NULL,
	[address] [nvarchar](255) NULL,
	CONSTRAINT "PK_Brand" PRIMARY KEY CLUSTERED
	(
	"brandID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
)ON [Primary]

GO
 CREATE INDEX "BrandName" ON "dbo"."Brand"("brandName")
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Suppliers--
CREATE TABLE [dbo].[Suppliers](
	[supplierID] [int] IDENTITY(1,1) NOT NULL,
	[companyName] [nvarchar] (40) NULL,
	[contactName] [nvarchar] (30) NULL,
	[contactTitle] [nvarchar] (30) NULL,
	[address] [nvarchar](255) NULL,
	[city] [nvarchar](15) NULL,
	[postalCode] [nvarchar](10) NULL,
	[phone] [nvarchar](24) NULL,
	[fax] [nvarchar](24) NULL,
	[homePage] [nvarchar](max) NULL,
	[description] [nvarchar](255) NULL,
	CONSTRAINT "PK_Supplier" PRIMARY KEY CLUSTERED
	(
	"supplierID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
)ON [Primary]

GO
 CREATE  INDEX "CompanyName" ON "dbo"."Suppliers"("companyName")
GO
 CREATE  INDEX "ContactName" ON "dbo"."Suppliers"("contactName")
GO
 CREATE  INDEX "Address" ON "dbo"."Suppliers"("address")
GO
 CREATE  INDEX "PostalCode" ON "dbo"."Suppliers"("postalCode")
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Warehouse--
CREATE TABLE [dbo].[Warehouse](
	[warehouseID] [int] IDENTITY(1,1) NOT NULL,
	[companyName] [nvarchar] (40) NULL,
	[warehouseName] [nvarchar](50)  NULL,
	[address] [nvarchar](255) NULL,
	[city] [nvarchar](15) NULL,
	[phone] [nvarchar](24) NULL,
	[fax] [nvarchar](24) NULL,
	[postalCode] [nvarchar](10) NULL,
	[description] [nvarchar](255) NULL,
	CONSTRAINT "PK_Warehouse" PRIMARY KEY CLUSTERED
	(
	"warehouseID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
)ON [Primary]

GO
 CREATE INDEX "CompanyName" ON "dbo"."Warehouse"("companyName")
GO
 CREATE INDEX "WarehouseName" ON "dbo"."Warehouse"("warehouseName")
GO
CREATE INDEX "Address" ON "dbo"."Warehouse"("address")
GO
 CREATE INDEX "PostalCode" ON "dbo"."Warehouse"("postalCode")
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Branch--
CREATE TABLE [dbo].[Branch](
	[branchID] [int] IDENTITY(1,1) NOT NULL,
	[companyName] [nvarchar] (40) NULL,
	[branchName] [nvarchar](50)  NULL,
	[address] [nvarchar](255) NULL,
	[city] [nvarchar](15) NULL,
	[phone] [nvarchar](24) NULL,
	[fax] [nvarchar](24) NULL,
	[postalCode] [nvarchar](10) NULL,
	[description] [nvarchar](255) NULL,
CONSTRAINT "PK_Branch" PRIMARY KEY CLUSTERED
	(
	"branchID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
)ON [Primary]

GO
 CREATE INDEX "CompanyName" ON "dbo"."Branch"("companyName")
GO
 CREATE INDEX "BranchName" ON "dbo"."Branch"("branchName")
GO
CREATE INDEX "Address" ON "dbo"."Branch"("address")
GO
 CREATE INDEX "PostalCode" ON "dbo"."Branch"("postalCode")
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Categories--
CREATE TABLE [dbo].[Categories](
	[categoryID] [int] IDENTITY(1,1) NOT NULL,
	[categoryName] [nvarchar](50) NULL,
	[restockThreshold] [int] NULL,
	[description] [nvarchar](255) NULL,
	CONSTRAINT "PK_Category" PRIMARY KEY CLUSTERED
	(
	"categoryID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
)ON [Primary]

GO
 CREATE INDEX "CategoryName" ON "dbo"."Categories"("categoryName")
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Import--
CREATE TABLE [dbo].[Import](
	[importID] [int] IDENTITY(1,1) NOT NULL,
	[supplierID] [int] NOT NULL,
	[brandID] [int] NOT NULL,
	[branchID] [int] NOT NULL,
	[categoryID] [int] NOT NULL,
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
	CONSTRAINT "PK_Import" PRIMARY KEY CLUSTERED
	(
	"importID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Import_Supplier" FOREIGN KEY 
	(
		"supplierID"
	) REFERENCES "dbo"."Suppliers" (
		"supplierID"
	),

	CONSTRAINT "FK_Import_Brand" FOREIGN KEY 
	(
		"brandID"
	) REFERENCES "dbo"."Brand" (
		"brandID"
	),

	CONSTRAINT "FK_Import_Branch" FOREIGN KEY 
	(
		"branchID"
	) REFERENCES "dbo"."Branch" (
		"branchID"
	),

	CONSTRAINT "FK_Import_Category" FOREIGN KEY 
	(
		"categoryID"
	) REFERENCES "dbo"."Categories" (
		"categoryID"
	),

)ON [Primary]

GO
 CREATE INDEX "Branch" ON "dbo"."Import"("branchID")
GO
 CREATE INDEX "CompanyName" ON "dbo"."Import"("companyName")
GO
CREATE INDEX "Address" ON "dbo"."Import"("address")
GO
 CREATE INDEX "PostalCode" ON "dbo"."Import"("postalCode")
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Inventory Status--
CREATE TABLE InventoryStatus
(
    statusID INT NOT NULL,
    statusName NVARCHAR(50) NULL
	CONSTRAINT "PK_Status" PRIMARY KEY CLUSTERED
	(
	"statusID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

)ON [Primary]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Products--
CREATE TABLE [dbo].[Products](
	[productID] [int] IDENTITY(1,1) NOT NULL,
	[warehouseID] [int] NOT NULL,
	[categoryID] [int] NOT NULL,
	[brandID] [int] NOT NULL,
	[supplierID] [int] NOT NULL,
	[productName] [nvarchar](255) NULL,
	[imageURL] [nvarchar](255) NULL,
	[unitPrice] [money] NULL CONSTRAINT "DF_Products_UnitPrice" DEFAULT (0),
	[quantity] [int] NULL,
	[quantitySold] [int] NULL,
	[quantityInStock] [int] NULL,
	[unit] [nvarchar](50) NULL,
	[newAdjustment] [int]NULL CONSTRAINT "DF_Products_AdjustmentThreshold" DEFAULT (0),
	[description] [nvarchar](255) NULL,
	[viewCount] [int] NULL,
	[discount] [int] NULL,
	[feedbackID] [int]NOT NULL,
	[avaliable] [int] NOT NULL,		
	CONSTRAINT "PK_Products" PRIMARY KEY  CLUSTERED 
	(
		"productID"
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Products_Warehouse" FOREIGN KEY 
	(
		"warehouseID"
	) REFERENCES "dbo"."Warehouse" (
		"warehouseID"
	),
	CONSTRAINT "FK_Products_Categories" FOREIGN KEY 
	(
		"categoryID"
	) REFERENCES "dbo"."Categories" (
		"categoryID"
	),
	CONSTRAINT "FK_Products_Brands" FOREIGN KEY 
	(
		"brandID"
	) REFERENCES "dbo"."Brand" (
		"brandID"
	),
	CONSTRAINT "FK_Products_Suppliers" FOREIGN KEY 
	(
		"supplierID"
	) REFERENCES "dbo"."Suppliers" (
		"supplierID"
	),
	CONSTRAINT "FK_Products_Feedbacks" FOREIGN KEY 
	(
		"feedbackID"
	) REFERENCES "dbo"."Feedbacks" (
		"feedbackID"
	),
	CONSTRAINT "FK_Products_InventoryStatus" FOREIGN KEY 
	(
		"avaliable"
	) REFERENCES "dbo"."InventoryStatus" (
		"statusID"
	),
	CONSTRAINT "CK_Products_UnitPrice" CHECK (unitPrice >= 0),
	CONSTRAINT "CK_QuantityPro" CHECK (quantity <= quantityInStock AND quantitySold <= quantity)

)On [Primary]

GO
 CREATE  INDEX "CategoriesProducts" ON "dbo"."Products"("categoryID")
GO
 CREATE  INDEX "CategoryID" ON "dbo"."Products"("categoryID")
GO
 CREATE  INDEX "BrandProducts" ON "dbo"."Products"("brandID")
GO
 CREATE  INDEX "Brand" ON "dbo"."Products"("brandID")
GO
 CREATE  INDEX "ProductName" ON "dbo"."Products"("productName")
GO
 CREATE  INDEX "SupplierID" ON "dbo"."Products"("supplierID")
GO
 CREATE  INDEX "SuppliersProducts" ON "dbo"."Products"("supplierID")
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table ThAdj--
CREATE TABLE [dbo].[ThresholdAdjustment](
	[ID] [int] NOT NULL,
	[productID] [int] NOT NULL,
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
		"productID"
	) REFERENCES "dbo"."Products" (
		"productID"
	),
)On [Primary]

GO
 CREATE  INDEX "Status" ON "dbo"."ThresholdAdjustment"("statusThresholdAdjustments")
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Cart--
CREATE TABLE [dbo].[Cart](
	[cartID] [int] NOT NULL,
	[productID] [int] NOT NULL,
	[customerID] [int] NOT NULL,
	[quantity] [int]NULL,
	[discount] [int]NULL,
	[totalPrice] [money] NULL,

	CONSTRAINT "PK_Cart" PRIMARY KEY CLUSTERED
	(
	"cartID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Cart_Product" FOREIGN KEY 
	(
		"productID"
	) REFERENCES "dbo"."Products" (
		"productID"
	),
	CONSTRAINT "FK_Cart_Customer" FOREIGN KEY 
	(
		"customerID"
	) REFERENCES "dbo"."Customers" (
		"customerID"
	),
	CONSTRAINT "CK_Discount" CHECK (Discount >= 0 and (Discount <= 50)),
	CONSTRAINT "CK_Quantity" CHECK (Quantity > 0),
	CONSTRAINT CK_TotalPriceEqualsUnitPriceAmount
    CHECK (totalPrice >= 0),
)ON [Primary]

GO
 CREATE INDEX "CustomerID" ON "dbo"."Cart"("customerID")
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table Orders--
CREATE TABLE dbo.Orders (
    [orderID] [INT] NOT NULL,
    [customerID] [INT] NOT NULL,
	[employeeID] [INT] NOT NULL,
    [orderDate] [DATETIME] NULL,
    [shipDate] [DATETIME] NULL,
	[shipAddress] [nvarchar](255) NULL,
	[note] [nvarchar](max) NULL,
    [status] [NVARCHAR](50) NULL,
	CONSTRAINT "PK_Orders" PRIMARY KEY CLUSTERED 
	(
	orderID ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

    CONSTRAINT "FK_Orders_Customers" FOREIGN KEY ("customerID") REFERENCES "dbo"."Customers"("CustomerID"),
	CONSTRAINT "FK_Orders_Employees" FOREIGN KEY ("employeeID") REFERENCES "dbo"."Employees"("employeeID")
)ON [Primary]

GO
 CREATE INDEX "CustomerID" ON "dbo"."Orders"("customerID")
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Create Table OrderDetails
CREATE TABLE [dbo].[OrdersDetails](
	[orderID] [int]NOT NULL,
	[cartID] [int]NOT NULL,
	[paymentMethod][nvarchar](255) NULL,
	CONSTRAINT "PK_Order_Details" PRIMARY KEY  CLUSTERED 
	(
		"orderID",
		"cartID"
	),
	CONSTRAINT "FK_Order_Details_Orders" FOREIGN KEY 
	(
		"orderID"
	) REFERENCES "dbo"."Orders" (
		"orderID"
	),
	CONSTRAINT "FK_Order_Details_Carts" FOREIGN KEY 
	(
		"cartID"
	) REFERENCES "dbo"."Cart" (
		"cartID"
	),
	
)

GO
 CREATE  INDEX "OrderID" ON "dbo"."OrdersDetails"("orderID")
GO
 CREATE  INDEX "OrdersOrder_Details" ON "dbo"."OrdersDetails"("orderID")
GO
 CREATE  INDEX "CartID" ON "dbo"."OrdersDetails"("cartID")
GO
 CREATE  INDEX "CartOrder_Details" ON "dbo"."OrdersDetails"("cartID")
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--SaleTg--
CREATE TABLE [dbo].[SalesTarget](
	[orderID] [int]NOT NULL,
	[target] [money] NULL,
	[sales] [money] NULL,
	[status] [bit] NULL,
	CONSTRAINT "PK_STg" PRIMARY KEY  CLUSTERED 
	(
		"orderID" ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],

	CONSTRAINT "FK_Target" FOREIGN KEY 
	(
		"orderID"
	) REFERENCES "dbo"."Orders" (
		"orderID"
	),	
)

GO
 CREATE  INDEX "Target" ON "dbo"."SalesTarget"("orderID")
GO
CREATE  INDEX "Status" ON "dbo"."SalesTarget"("status")
GO


create view "Orders Qry" AS
SELECT Orders.OrderID, Orders.CustomerID, Orders.EmployeeID, Orders.OrderDate, Orders.ShipDate, 
	Orders.ShipAddress, Orders.note, Orders.status,  
	Customers.fullname, Customers.Address, Customers.phone, Customers.email,
	CreditCard.cardName, CreditCard.cardNumber,CreditCard.cvvNumber,CreditCard.valueFrom,CreditCard.expirationDate,
	Customers.point,
	customers.rank
FROM Customers INNER JOIN Orders ON Customers.CustomerID = Orders.CustomerID 
INNER JOIN CreditCard ON Customers.cardID = CreditCard.cardID
GO


create view "Products Above Average Price" AS
SELECT Products.ProductName, Products.UnitPrice
FROM Products
WHERE Products.UnitPrice>(SELECT AVG(UnitPrice) From Products)
--ORDER BY Products.UnitPrice DESC
GO

create view "Products by Category" AS
SELECT Categories.CategoryName, Products.ProductName, Products.quantity, Products.quantityInStock, Products.quantitySold, Categories.restockThreshold
FROM Categories INNER JOIN Products ON Categories.CategoryID = Products.CategoryID
--ORDER BY Categories.CategoryName, Products.ProductName
GO

CREATE VIEW OrderByQuarter AS
SELECT
    OrderID,
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
    CardID,
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
            Orders.OrderID,
            Orders.CustomerID,
            Orders.EmployeeID,
            Orders.OrderDate,
            Orders.ShipDate,
            Orders.ShipAddress,
            Orders.Note,
            Orders.Status,
            Customers.FullName,
            Customers.Address,
            Customers.Phone,
            Customers.Email,
            Customers.CardID,
            Customers.Point,
            Customers.Rank
        FROM
            Customers
        INNER JOIN Orders ON Customers.CustomerID = Orders.CustomerID
        INNER JOIN CreditCard ON Customers.CardID = CreditCard.CardID
    ) AS Subquery;

GO

CREATE VIEW InvoiceView AS
SELECT
    O.orderID,
    O.customerID,
    O.employeeID,
    O.orderDate,
    O.shipDate,
    O.shipAddress,
    O.note,
    O.status,
    C.cartID,
    C.productID,
    C.quantity,
    C.discount,
    C.totalPrice,
    OD.paymentMethod
FROM
    dbo.Orders AS O
JOIN
    dbo.OrdersDetails AS OD ON O.orderID = OD.orderID
JOIN
    dbo.Cart AS C ON OD.cartID = C.cartID;

GO

CREATE VIEW "Order Subtotals" AS
SELECT
    OD.OrderID,
    SUM(CONVERT(money, (P.unitPrice * C.quantity * (1 - C.Discount) / 100)) * 100) AS Subtotal
FROM
    dbo.OrdersDetails AS OD
JOIN
    dbo.Cart AS C ON C.cartID = OD.cartID
JOIN
    dbo.Products AS P ON P.productID = C.productID
GROUP BY
    OD.OrderID;
GO

create view "Sales Totals by Amount" AS
SELECT "Order Subtotals".Subtotal AS SaleAmount, Orders.OrderID, Customers.fullname, Orders.ShipDate,DATEPART(QUARTER, Orders.ShipDate) AS Quarter
FROM 	Customers INNER JOIN 
		(Orders INNER JOIN "Order Subtotals" ON Orders.OrderID = "Order Subtotals".OrderID) 
	ON Customers.CustomerID = Orders.CustomerID
WHERE ("Order Subtotals".Subtotal >2500)
GROUP BY DATEPART(QUARTER, Orders.ShipDate);
GO

create view "Summary of Sales by Quarter" AS
SELECT Orders.ShipDate, Orders.OrderID, "Order Subtotals".Subtotal
FROM Orders INNER JOIN "Order Subtotals" ON Orders.OrderID = "Order Subtotals".OrderID
WHERE Orders.ShipDate IS NOT NULL
--ORDER BY Orders.ShippedDate
GO

create view "Summary of Sales by Year" AS
SELECT Orders.ShipDate, Orders.OrderID, "Order Subtotals".Subtotal
FROM Orders INNER JOIN "Order Subtotals" ON Orders.OrderID = "Order Subtotals".OrderID
WHERE Orders.ShipDate IS NOT NULL
--ORDER BY Orders.ShippedDate
GO
create procedure "Ten Most Expensive Products" AS
SET ROWCOUNT 10
SELECT Products.ProductName AS TenMostExpensiveProducts, Products.UnitPrice
FROM Products
ORDER BY Products.UnitPrice DESC
GO

create procedure "Ten Best Seller Products" AS
SET ROWCOUNT 10
SELECT Products.ProductName AS TenBestSellerProducts, Products.quantity
FROM Products
ORDER BY max(Products.quantity)
GO

create procedure "Sales by Year" 
	@Beginning_Date DateTime, @Ending_Date DateTime AS
SELECT Orders.ShipDate, Orders.OrderID, "Order Subtotals".Subtotal, DATENAME(yy,ShipDate) AS Year
FROM Orders INNER JOIN "Order Subtotals" ON Orders.OrderID = "Order Subtotals".OrderID
WHERE Orders.ShipDate Between @Beginning_Date And @Ending_Date
GO

CREATE PROCEDURE UpdateTotalProductQuantities
AS
BEGIN
    UPDATE P
    SET P.quantity = 
        CASE 
            WHEN (P.quantity + P.quantityInStock) > C.restockThreshold THEN C.restockThreshold - P.quantityInStock
            ELSE CASE WHEN P.quantity > P.quantityInStock THEN P.quantityInStock ELSE P.quantity END
        END
    FROM dbo.Products P
    INNER JOIN dbo.Categories C ON P.categoryID = C.categoryID;
END
GO

--=======================INSERT DATA=====================--
set quoted_identifier on
go
set identity_insert "Permission" on
go
ALTER TABLE "Permission" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Permission" ("permissionID","permissionName") VALUES (1,'manager');
INSERT INTO "Permission" ("permissionID","permissionName") VALUES (2,'employee');
INSERT INTO "Permission" ("permissionID","permissionName") VALUES (3,'customer');
GO
set identity_insert "Permission" off
go
ALTER TABLE "Permission" CHECK CONSTRAINT ALL
go

------------------------------------
set quoted_identifier on
go
set identity_insert "CreditCard" on
go
ALTER TABLE "CreditCard" NOCHECK CONSTRAINT ALL
go
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (1,'Pham Hieu Tho', '1234567890123456', '2023-01-01', '2025-12-31', 123, 'credit card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (2,'Ly Minh Nghia', '2345678901234567', '2023-01-01', '2025-12-31', 456, 'credit card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (3,'Pham Huu Nhan', '3456789012345678', '2023-01-01', '2025-12-31', 789, 'credit card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (4,'Nguyen Minhh Tuan', '4567890123456789', '2023-01-01', '2025-12-31', 234, 'prepaid card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (5,'Nguyen Thi My Hao', '5678901234567890', '2023-01-01', '2025-12-31', 567, 'prepaid card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (6,'Ong Kim Thanh', '6789012345678901', '2023-01-01', '2025-12-31', 890, 'prepaid card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (7,'Nguyen Trong Tri', '7890123456789012', '2023-01-01', '2025-12-31', 345, 'debit card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (8,'Nguyen Tan Phat', '8901234567890123', '2023-01-01', '2025-12-31', 678, 'debit card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (9,'Truong Ngoc Anh', '9012345678901234', '2023-01-01', '2025-12-31', 901, 'debit card');
    -- Add more rows as needed
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (10,'Nguyen Thi Anh Thu', '0123456789012345', '2023-01-01', '2025-12-31', 123, 'credit card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (11,'Pham Van Hien', '1234567890123456', '2023-01-01', '2025-12-31', 456, 'credit card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (12,'To Ngoc Lan', '2345678901234567', '2023-01-01', '2025-12-31', 789, 'credit card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (13,'Truong Thi Ngoc Trang', '3456789012345678', '2023-01-01', '2025-12-31', 234, 'prepaid card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (14,'Nguyen Ngoc Hien', '4567890123456789', '2023-01-01', '2025-12-31', 567, 'prepaid card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (15,'Nguyen Thanh Dat', '5678901234567890', '2023-01-01', '2025-12-31', 890, 'prepaid card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (16,'Dang Minh Dat', '6789012345678901', '2023-01-01', '2025-12-31', 345, 'debit card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (17,'Tran Thi Thu Thao', '7890123456789012', '2023-01-01', '2025-12-31', 678, 'debit card');
INSERT INTO "CreditCard" ("cardID","cardName", "cardNumber", "valueFrom", "expirationDate", "cvvNumber", "cardType") VALUES (18,'Vuong Nguyen Kim Tuyen', '8901234567890123', '2023-01-01', '2025-12-31', 901, 'debit card');

GO
set identity_insert "CreditCard" off
go
ALTER TABLE "CreditCard" CHECK CONSTRAINT ALL
go

-------------------------------------------
set quoted_identifier on
go
set identity_insert "Accounts" on
go
ALTER TABLE "Accounts" NOCHECK CONSTRAINT ALL
go
---User with permission 1-----
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword","permissionID", "description") VALUES (1,'Admin', 'Admin', HASHBYTES('SHA2_256', 'Admin'),1, 'Manager');
---User with permission 2-----
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (2,'quangtuong', 'Admin', HASHBYTES('SHA2_256', 'Admin'), 2, 'Employee');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (3,'hieutho', 'Admin', HASHBYTES('SHA2_256', 'Admin'), 2, 'Employee');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (4,'huunhan', 'Admin', HASHBYTES('SHA2_256', 'Admin'), 2, 'Employee');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (5,'minhnghia', 'Admin', HASHBYTES('SHA2_256', 'Admin'), 2, 'Employee');
---User with permission 3-----
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (6,'hieutho', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (7,'huunhan', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (8,'minhnghia', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (9,'minhtuan', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (10,'myhao', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (11,'kimthanh', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (12,'trongtri', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (13,'tanphat', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (14,'ngocanh', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (15,'anhthu', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (16,'vanhien', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (17,'ngoclan', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (18,'ngoctrang', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (19,'ngochien', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (20,'thanhdat', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (22,'minhdat', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (22,'thuthao', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
INSERT INTO "Accounts" ("accountID","userName", "password", "hashedPassword", "permissionID", "description") VALUES (23,'kimtuyen', 'user', HASHBYTES('SHA2_256', 'user'), 3, 'Customer');
GO
set identity_insert "Accounts" off
go
ALTER TABLE "Accounts" CHECK CONSTRAINT ALL
go

-------------------------------------------
set quoted_identifier on
go
set identity_insert "Managers" on
go
ALTER TABLE "Managers" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Managers" ("managerID","fullname","accountID","address","email","phone") VALUES (1,'Manager',1,'Can Tho','manager.zipmart@gamil.com',' 0292 3731 072');
GO
set identity_insert "Managers" off
go
ALTER TABLE "Managers" CHECK CONSTRAINT ALL
go

-------------------------------------------
set quoted_identifier on
go
set identity_insert "Employees" on
go
ALTER TABLE "Employees" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Employees" ("employeeID","fullname","accountID","imageURL","birthDate","address","email","phone","Notes") VALUES (1,'To Quang Tuong',2,'img\uploads\emp\man.jpg','07-25-2003','Can Tho','qtuong.257@gmail.com','0917895327','My note');
INSERT INTO "Employees" ("employeeID","fullname","accountID","imageURL","birthDate","address","email","phone","Notes") VALUES (2,'Pham Hieu Tho',2,'img\uploads\emp\woman.jpg','07-04-2003','Can Tho','hieutho1510@gmail.com','0938973817','My note');
INSERT INTO "Employees" ("employeeID","fullname","accountID","imageURL","birthDate","address","email","phone","Notes") VALUES (3,'Pham Huu Nhan',2,'img\uploads\emp\man.jpg','07-25-2003','Can Tho','huunhan.service@gmail.com','0123456789','Off');
INSERT INTO "Employees" ("employeeID","fullname","accountID","imageURL","birthDate","address","email","phone","Notes") VALUES (4,'Ly Minh Nghia',2,'img\uploads\emp\man.jpg','07-25-2003','Can Tho','minhnghia.service@gmail.com','0123456789','Off');
GO
set identity_insert "Employees" off
go
ALTER TABLE "Employees" CHECK CONSTRAINT ALL
go

-------------------------------------------
set quoted_identifier on
go
set identity_insert "Customers" on
go
ALTER TABLE "Customers" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "point", "rank"	) VALUES (1, 1, 6, 'Pham Hieu Tho', 'Can Tho', '0938973817', 'hieutho1510@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "point", "rank"	) VALUES (2, 2, 7, 'Ly Minh Nghia', 'Can Tho', '0123456781', 'minhnghia.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "point", "rank"	) VALUES (3, 3, 8, 'Pham Huu Nhan', 'Can Tho', '0123456782', 'huunhan.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "point", "rank"	) VALUES (4, 4, 9, 'Nguyen Minhh Tuan', 'Can Tho', '0123436789', 'minhtuan.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "point", "rank"	) VALUES (5, 5, 10, 'Nguyen Thi My Hao', 'Can Tho', '0123456789', 'myhao.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "point", "rank"	) VALUES (6, 6, 11, 'Ong Kim Thanh', 'Can Tho', '0123456759', 'kimthanh.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "point", "rank"	) VALUES (7, 7, 12, 'Nguyen Trong Tri', 'Can Tho', '0123466789', 'trongtri.cus0@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "point", "rank"	) VALUES (8, 8, 13, 'Nguyen Tan Phat', 'Can Tho', '0123457789', 'tanphat.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "point", "rank"	) VALUES (9, 9, 14, 'Truong Ngoc Anh', 'Can Tho', '0123458789', 'ngocanh.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "point", "rank"	) VALUES (10, 10, 15, 'Nguyen Thi Anh Thu', 'Can Tho', '0923456789', 'anhthu.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "point", "rank"	) VALUES (11, 11, 16, 'Pham Van Hien', 'Can Tho', '0123423789', 'vanhien.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "point", "rank"	) VALUES (12, 12, 17, 'To Ngoc Lan', 'Can Tho', '0123456119', 'ngoclan.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "point", "rank"	) VALUES (13, 13, 18, 'Truong Thi Ngoc Trang', 'Can Tho', '0123456789', 'ngoctrang.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "point", "rank"	) VALUES (14, 14, 19, 'Nguyen Ngoc Hien', 'Can Tho', '0123453189', 'ngochien.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "point", "rank"	) VALUES (15, 15, 20, 'Nguyen Thanh Dat', 'Can Tho', '0125356789', 'thanhdat.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "point", "rank"	) VALUES (16, 16, 21, 'Dang Minh Dat', 'Can Tho', '0123453589', 'minhdat.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "point", "rank"	) VALUES (17, 17, 22, 'Tran Thi Thu Thao', 'Can Tho', '0112456789', 'thuthao.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 100, 'Gold');
INSERT INTO "Customers" ("customerID","cardID", "accountID", "fullname", "address", "phone", "email", "birthDate", "imageURL", "point", "rank"	) VALUES (18, 18, 23, 'Vuong Nguyen Kim Tuyen', 'Can Tho', '0753456789', 'kimtuyen.cus@gmail.com', '07-04-2003', 'img\uploads\cus\woman.jpg', 100, 'Gold');

GO
set identity_insert "Customers" off
go
ALTER TABLE "Customers" CHECK CONSTRAINT ALL
go
------------------------------------
set quoted_identifier on
go
set identity_insert "Categories" on
go
ALTER TABLE "Categories" NOCHECK CONSTRAINT ALL
go
INSERT INTO "Categories" ("categoryID","CategoryName", "Description", "restockThreshold") VALUES (1,'Household Appliances', 'Home electronic devices', 500);
INSERT INTO "Categories" ("categoryID","CategoryName", "Description", "restockThreshold") VALUES (2,'Spices', 'Various cooking spices', 500);
INSERT INTO "Categories" ("categoryID","CategoryName", "Description", "restockThreshold") VALUES (3,'Meat', 'Various meat products', 500);
INSERT INTO "Categories" ("categoryID","CategoryName", "Description", "restockThreshold") VALUES (4,'Seafood', 'Various seafood products', 500);
INSERT INTO "Categories" ("categoryID","CategoryName", "Description", "restockThreshold") VALUES (5,'Vegetables', 'Fresh vegetables', 500);
INSERT INTO "Categories" ("categoryID","CategoryName", "Description", "restockThreshold") VALUES (6,'Convenience Products', 'Convenient and ready-to-use products', 500);
INSERT INTO "Categories" ("categoryID","CategoryName", "Description", "restockThreshold") VALUES (7,'Fashion', 'Clothing and accessories', 500);
INSERT INTO "Categories" ("categoryID","CategoryName", "Description", "restockThreshold") VALUES (8,'Aquatic Products', 'Various aquatic products', 500);
INSERT INTO "Categories" ("categoryID","CategoryName", "Description", "restockThreshold") VALUES (9,'Fruits', 'Fresh fruits', 500);
GO
set identity_insert "Categories" off
go
ALTER TABLE "Categories" CHECK CONSTRAINT ALL
go