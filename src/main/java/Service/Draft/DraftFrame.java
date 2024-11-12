package Service.Draft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DraftFrame extends JFrame implements ActionListener {
    JTextArea draftTextArea;
    private String draft;

    public DraftFrame(String draft) {
        this.draft = draft;
        setTitle("草稿");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        draftTextArea = new JTextArea(draft);
        JScrollPane scrollPane = new JScrollPane(draftTextArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("保存草稿");

        saveButton.addActionListener(this);

        buttonPanel.add(saveButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("保存草稿")) {
            draft = draftTextArea.getText();
            DraftService draftService = new DraftService();
            if (draftService.getContent() == null) {
                draftService.insertDraft(draft);
            } else {
                draftService.updateDraft(draft);
            }
            JOptionPane.showMessageDialog(this, "草稿已保存");
        }
    }
}