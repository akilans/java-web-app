#!/bin/bash

echo "=== Book API Test Script ==="
echo

echo "1. Testing GET /api/books (Get all books)"
curl -s http://localhost:8080/api/books | python3 -m json.tool
echo

echo "2. Testing GET /api/books/1 (Get book by ID)"
curl -s http://localhost:8080/api/books/1 | python3 -m json.tool
echo

echo "3. Testing POST /api/books (Create new book)"
curl -s -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{
    "title": "New Test Book",
    "author": "Test Author",
    "isbn": "978-0000000000",
    "publicationDate": "2023-01-01",
    "description": "A test book for API demonstration",
    "price": 25.99
  }' | python3 -m json.tool
echo

echo "4. Testing GET /api/books/search?q=Spring (Search books)"
curl -s "http://localhost:8080/api/books/search?q=Spring" | python3 -m json.tool
echo

echo "5. Testing GET /api/books/price-range (Books by price range)"
curl -s "http://localhost:8080/api/books/price-range?minPrice=40&maxPrice=60" | python3 -m json.tool
echo

echo "=== API Test Complete ==="
