package server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class EditReservation {

	private JFrame frame;
	private String command;
	private JPanel panel_2;
	private JLabel lblRoomType;
	private JScrollPane scrollPane;
	private JList list;
	private JList list_1;
	private JScrollPane scrollPane_1;
	private JList list_2;
	private JScrollPane scrollPane_2;
	private JList list_3;
	private JLabel lblCheckinDate;
	private JScrollPane scrollPane_3;
	private JList list_4;
	private JScrollPane scrollPane_4;
	private JList list_5;
	private JScrollPane scrollPane_5;
	private JList list_6;
	private JLabel lblCheckoutDate;
	private JScrollPane scrollPane_6;
	private JButton button;
	private JCheckBox chckbxNewCheckBox;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditReservation window = new EditReservation();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EditReservation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("Edit reservation");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 21));
		lblNewLabel.setBounds(176, 6, 177, 58);
		frame.getContentPane().add(lblNewLabel);
		JButton btnNewButton = new JButton("Back ");
		btnNewButton.setBounds(23, 327, 111, 29);
		frame.getContentPane().add(btnNewButton);
		lblRoomType = new JLabel("Room type:");
		lblRoomType.setBounds(382, 222, 89, 16);
		frame.getContentPane().add(lblRoomType);
		panel_2 = new JPanel();
		panel_2.setBounds(460, 223, 58, 36);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(0, 0, 58, 39);
		panel_2.add(scrollPane_6);
		list = new JList();
		scrollPane_6.setViewportView(list);
		list.setSelectedIndices(new int[] {0});
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Single", "Double"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectedIndex(0);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 125, 58, 58);
		frame.getContentPane().add(scrollPane);
		list_1 = new JList<Integer>();
		list_1.setModel(new AbstractListModel() {
			String[] values = new String[] {"dd", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_1.setSelectedIndex(0);
		scrollPane.setViewportView(list_1);
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(94, 125, 84, 58);
		frame.getContentPane().add(scrollPane_1);
		list_2 = new JList<Integer>();
		list_2.setModel(new AbstractListModel() {
			String[] values = new String[] {"mm", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_2.setSelectedIndex(0);
		scrollPane_1.setViewportView(list_2);
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(190, 125, 73, 58);
		frame.getContentPane().add(scrollPane_2);
		list_3 = new JList<Integer>();
		list_3.setModel(new AbstractListModel() {
			String[] values = new String[] {"yyyy", "2019", "2020", "2021", "2022"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_3.setSelectedIndex(0);
		scrollPane_2.setViewportView(list_3);
		lblCheckinDate = new JLabel("Check-in date:");
		lblCheckinDate.setBounds(23, 84, 110, 16);
		frame.getContentPane().add(lblCheckinDate);
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(26, 233, 58, 58);
		frame.getContentPane().add(scrollPane_3);
		list_4 = new JList<Integer>();
		list_4.setModel(new AbstractListModel() {
			String[] values = new String[] {"dd", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_4.setSelectedIndex(0);
		scrollPane_3.setViewportView(list_4);
		scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(94, 233, 84, 58);
		frame.getContentPane().add(scrollPane_4);
		list_5 = new JList<Integer>();
		list_5.setModel(new AbstractListModel() {
			String[] values = new String[] {"mm", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_5.setSelectedIndex(0);
		scrollPane_4.setViewportView(list_5);
		scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(190, 233, 73, 58);
		frame.getContentPane().add(scrollPane_5);
		list_6 = new JList<Integer>();
		list_6.setModel(new AbstractListModel() {
			String[] values = new String[] {"yyyy", "2019", "2020", "2021", "2022"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_6.setSelectedIndex(0);
		scrollPane_5.setViewportView(list_6);
		lblCheckoutDate = new JLabel("Check-out date:");
		lblCheckoutDate.setBounds(24, 205, 110, 16);
		frame.getContentPane().add(lblCheckoutDate);
		button = new JButton("Confirm");
		button.setBounds(190, 327, 117, 29);
		frame.getContentPane().add(button);
		chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(422, 123, 128, 23);
		frame.getContentPane().add(chckbxNewCheckBox);
		lblNewLabel_1 = new JLabel("Smoking:");
		lblNewLabel_1.setBounds(357, 127, 67, 16);
		frame.getContentPane().add(lblNewLabel_1);
	}

}
