#!/usr/bin/env python3
"""
Demo script showing how to use the PDF skill
This demonstrates the PDF processing capabilities
"""

from pypdf import PdfReader, PdfWriter
import os

def demo_pdf_processing():
    """Demonstrate PDF processing capabilities"""
    
    # Create a simple demo PDF if none exists
    if not os.path.exists("demo.pdf"):
        print("Creating a demo PDF file...")
        # This would normally create a PDF, but for demo purposes
        print("Please provide a PDF file named 'demo.pdf' to test the PDF skill")
        return
    
    try:
        # Read the PDF
        reader = PdfReader("demo.pdf")
        print(f"✅ PDF loaded successfully!")
        print(f"📄 Number of pages: {len(reader.pages)}")
        
        # Extract text from first page
        if len(reader.pages) > 0:
            first_page = reader.pages[0]
            text = first_page.extract_text()
            print(f"📝 First page text preview:")
            print(text[:200] + "..." if len(text) > 200 else text)
        
        # Check for form fields
        if reader.get_fields():
            print(f"📋 Form fields found: {list(reader.get_fields().keys())}")
        else:
            print("📋 No form fields detected")
            
    except Exception as e:
        print(f"❌ Error processing PDF: {e}")

if __name__ == "__main__":
    print("🔧 PDF Skill Demo")
    print("=" * 30)
    demo_pdf_processing()