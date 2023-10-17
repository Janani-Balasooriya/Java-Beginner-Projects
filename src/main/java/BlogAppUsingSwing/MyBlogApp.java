package BlogAppUsingSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class BlogPost {
    private String title;
    private String content;

    public BlogPost(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}

public class MyBlogApp {
    private ArrayList<BlogPost> blogPosts;
    private JFrame mainFrame;
    private JTextArea textArea;

    public MyBlogApp() {
        blogPosts = new ArrayList<>();
        mainFrame = new JFrame("Simple BlogApp Using Swing");
        mainFrame.setSize(800, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createMainUI();
    }

    private void createMainUI() {
        JPanel panel = new JPanel();
        mainFrame.add(panel);
        panel.setLayout(new GridLayout(3, 1));

        JButton createButton = new JButton("Create a new blog post");
        JButton viewButton = new JButton("View all blog posts");
        JButton exitButton = new JButton("Exit");

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCreateBlogFrame();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openViewBlogsFrame();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(createButton);
        panel.add(viewButton);
        panel.add(exitButton);
    }

    private void openCreateBlogFrame() {
        JFrame createFrame = new JFrame("Create a new blog post");
        createFrame.setSize(800, 800);

        JPanel createPanel = new JPanel(new BorderLayout(10, 10));

        JTextField titleField = new JTextField(20);

        JTextArea contentArea = new JTextArea(5, 2);
        contentArea.setLineWrap(true);
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String content = contentArea.getText();
                if (!title.isEmpty() && !content.isEmpty()) {
                    BlogPost newBlogPost = new BlogPost(title, content);
                    blogPosts.add(newBlogPost);
                    JOptionPane.showMessageDialog(createFrame, "Blog post created!");
                    createFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(createFrame, "Please enter both title and content.");
                }
            }
        });

        createPanel.add(new JLabel("Title:"));
        createPanel.add(titleField, BorderLayout.NORTH);
        createPanel.add(new JLabel("Content:"));
        createPanel.add(contentArea, BorderLayout.CENTER);
        createPanel.add(submitButton, BorderLayout.SOUTH);

        createFrame.add(createPanel);
        createFrame.setVisible(true);
    }

    private void openViewBlogsFrame() {
        JFrame viewFrame = new JFrame("View all blog posts");
        viewFrame.setSize(500, 500);

        JPanel viewPanel = new JPanel(new BorderLayout());

        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        textArea.setText("All Blog Posts:\n");
        textArea.setLineWrap(true);
        textArea.setToolTipText("Enter Title");

        for (BlogPost post : blogPosts) {
            textArea.append("Title: " + post.getTitle() + "\n");
            textArea.append("Content: " + post.getContent() + "\n");
            textArea.append(
                    "--------------------------------------------------------------------------------------------------\n"
                            + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        viewPanel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewFrame.dispose();
            }
        });

        viewPanel.add(backButton, BorderLayout.SOUTH);

        viewFrame.add(viewPanel);
        viewFrame.setVisible(true);
    }

    public void run() {
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        MyBlogApp blogApp = new MyBlogApp();
        blogApp.run();
    }
}
