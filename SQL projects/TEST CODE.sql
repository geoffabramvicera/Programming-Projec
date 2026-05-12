-- Create the Books table for SQL Server
CREATE TABLE Books (
    BookID INT IDENTITY(1,1) PRIMARY KEY,
    Title VARCHAR(255) NOT NULL,
    Authors VARCHAR(255) NOT NULL,
    ISBN VARCHAR(20) UNIQUE NOT NULL,
    Genre VARCHAR(100),
    Publisher VARCHAR(255),
    PublicationYear INT,
    Language VARCHAR(50),
    Description TEXT  -- Note: In SQL Server, TEXT is deprecated; consider using VARCHAR(MAX) for better practice
);

-- 1. INSERT OPERATION: Add sample records
INSERT INTO Books (Title, Authors, ISBN, Genre, Publisher, PublicationYear, Language, Description) VALUES
('Where the Sidewalk Ends', 'Shel Silverstein', '978-0060572341', 'Poetry', 'HarperCollins', 1974, 'English', 'A collection of classic poems and drawings for children and adults.'),
('Dune', 'Frank Herbert', '978-0441172719', 'Science Fiction', 'Chilton Books', 1965, 'English', 'A monumental science fiction epic set on the desert planet Arrakis.'),
('The Secret History', 'Donna Tartt', '978-0679733372', 'Mystery/Fiction', 'Knopf', 1992, 'English', 'A tale of a group of classics students at a Vermont college who become involved in a murder.'),
('Becoming', 'Michelle Obama', '978-1524763138', 'Biography/Memoir', 'Crown Publishing Group', 2018, 'English', 'An intimate and powerful memoir by the former First Lady of the United States.');

-- 2. READ OPERATION: Retrieval Queries

-- 2.1. List ALL Available Books
SELECT * FROM Books;

-- 2.2. List Books by Specific Author
SELECT BookID, Title, PublicationYear FROM Books WHERE Authors = 'Frank Herbert';

-- 2.3. Find a Book by its ISBN
SELECT Title, Genre, Publisher FROM Books WHERE ISBN = '978-0679733372';

-- 2.4. List Books in a specific Genre
SELECT Title, Authors, PublicationYear FROM Books WHERE Genre LIKE '%Poetry%';

-- 3. UPDATE OPERATION: Modify Records
-- Example: Update the Publisher and Genre for the book with BookID 4
UPDATE Books 
SET Publisher = 'Penguin Random House', 
    Genre = 'Non-Fiction/Memoir' 
WHERE BookID = 4;

-- Verify the update
SELECT * FROM Books WHERE BookID = 4;

-- 4. DELETE OPERATION: Remove Records
-- Example: Remove the book with BookID 1
DELETE FROM Books WHERE BookID = 1;

-- Verify the deletion
SELECT * FROM Books;