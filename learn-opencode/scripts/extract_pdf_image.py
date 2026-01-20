import fitz
import sys

def extract_first_image(pdf_path, output_path="first_image.png"):
    doc = fitz.open(pdf_path)
    
    for page_num in range(len(doc)):
        page = doc[page_num]
        image_list = page.get_images()
        
        if image_list:
            xref = image_list[0][0]
            base_image = doc.extract_image(xref)
            image_bytes = base_image["image"]
            image_ext = base_image["ext"]
            
            if output_path == "first_image.png":
                output_path = f"first_image.{image_ext}"
            
            with open(output_path, "wb") as img_file:
                img_file.write(image_bytes)
            
            doc.close()
            print(f"Extracted first image to: {output_path}")
            return output_path
    
    doc.close()
    print("No images found in the PDF")
    return None

if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Usage: python extract_pdf_image.py <pdf_file> [output_path]")
        sys.exit(1)
    
    pdf_file = sys.argv[1]
    output = sys.argv[2] if len(sys.argv) > 2 else "first_image.png"
    
    extract_first_image(pdf_file, output)