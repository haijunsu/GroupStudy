# How to Use Skills in OpenCode

## 🎯 Overview

Skills are specialized capabilities that OpenCode can use to accomplish specific tasks. Think of them as "tools" or "expertise modules" that you can invoke when needed.

## 📋 Available Skills

### Document Skills
- **PDF**: Extract text, fill forms, merge/split PDFs
- **Word (docx)**: Create and manipulate Word documents  
- **PowerPoint (pptx)**: Generate presentations
- **Excel (xlsx)**: Create spreadsheets with charts

### Creative Skills
- **Algorithmic Art**: Generate generative art with p5.js
- **Canvas Design**: Create visual designs
- **Brand Guidelines**: Apply brand standards

### Technical Skills
- **MCP Builder**: Build Model Context Protocol servers
- **WebApp Testing**: Test web applications
- **Frontend Design**: Create UI/UX designs

## 💡 How to Invoke Skills

### Method 1: Direct Request
Simply mention the skill by name:

```
"Use the PDF skill to extract text from this document"
"Create algorithmic art using the algorithmic-art skill"
"Build an MCP server for the GitHub API"
```

### Method 2: Contextual Activation
OpenCode will automatically detect when a skill is needed based on your request:

```
"Fill out this PDF form" → PDF skill activates
"Create a generative art piece" → Algorithmic art skill activates  
"Build a tool to connect to the Slack API" → MCP builder skill activates
```

## 🛠️ Example Usage

### PDF Processing Example
```bash
# Ask OpenCode:
"Use the PDF skill to extract all form fields from contract.pdf"
```

### Algorithmic Art Example  
```bash
# Ask OpenCode:
"Create algorithmic art using the algorithmic-art skill with a flow field theme"
```

### MCP Server Example
```bash
# Ask OpenCode:  
"Use the mcp-builder skill to create an MCP server for the weather API"
```

## 🔧 Skill Configuration

Skills are configured in:
- `~/.opencode/skills/config.json` - Main configuration
- `~/.opencode/skills/anthropics-skills/` - Skill definitions

## 📚 Skill Documentation

Each skill has its own documentation:
- `~/.opencode/skills/anthropics-skills/skills/[skill-name]/SKILL.md`
- Look for examples, guidelines, and API references

## 🚀 Getting Started

1. **Start OpenCode**: `opencode`
2. **Make your request**: Mention the specific skill or describe the task
3. **Follow the guidance**: The skill will provide structured instructions and examples

## 💪 Pro Tips

- Be specific about which skill you want to use
- Reference the skill documentation for advanced features
- Skills can be combined (e.g., use brand-guidelines + canvas-design)
- Check the skill's examples and guidelines for best results