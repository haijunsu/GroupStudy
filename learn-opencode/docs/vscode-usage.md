# Using OpenCode in Visual Studio Code (VS Code)

This guide explains how to effectively use OpenCode within the VS Code environment.

## Why Use OpenCode in VS Code?

Using OpenCode inside VS Code allows you to:
- See code changes in real-time as OpenCode modifies your files.
- Easily run tests and build commands in a separate terminal.
- Use VS Code's powerful editor features alongside OpenCode's assistance.

## Prerequisites

- Visual Studio Code installed.
- OpenCode CLI installed and in your system PATH.
- Your project folder open in VS Code.

## Getting Started

1.  **Open your project in VS Code**:
    Launch VS Code and use `File > Open Folder...` to select your project directory.

2.  **Open the Integrated Terminal**:
    - Use the keyboard shortcut `Ctrl + ` ` (backtick) to toggle the terminal.
    - Or go to `View > Terminal` in the top menu.

3.  **Start OpenCode**:
    In the terminal, type the following command and press Enter:
    ```bash
    opencode
    ```

## Workflow Tips

### Split Terminal
You can split the terminal to run OpenCode in one pane and your build/test commands in another.
- Click the "Split Terminal" icon (or press `Cmd + \` on macOS / `Ctrl + \` on Windows/Linux).
- This allows you to keep OpenCode running while manually verifying changes.

### Real-time Updates
As OpenCode modifies files, VS Code will automatically update the editor view. You can see the changes happen live. If you have the file open, you can watch the code being written.

### Using VS Code Features
- **Diff View**: Use the Source Control tab to see a diff of changes made by OpenCode before committing.
- **Problems Tab**: Check the "Problems" tab for any syntax errors or issues introduced by changes.

## Additional Resources

- For more information on using OpenCode skills, see [How to Use Skills in OpenCode](../skills-usage-demo.md).
- For project-specific VS Code settings, check `.vscode/settings.json`.
