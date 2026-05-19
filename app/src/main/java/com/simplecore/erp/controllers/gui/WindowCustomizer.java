
package com.simplecore.erp.controllers.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WindowCustomizer {

    private int pX, pY;
    private JPanel titlePanel;
    private JFrame frame;

    public WindowCustomizer(JFrame frame, JPanel titlePanel) {
        this.frame = frame;
        this.titlePanel = titlePanel;
        setWindowMovable();
        setWindowResizable();
    }

    private void setWindowMovable() {
        // MouseListener to capture mouse position when pressed
        titlePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pX = e.getX();
                pY = e.getY();
            }
        });

        // MouseMotionListener to move the window
        titlePanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int deltaX = e.getX() - pX;
                int deltaY = e.getY() - pY;
                frame.setLocation(frame.getLocation().x + deltaX, frame.getLocation().y + deltaY);
            }
        });
    }

    private void setWindowResizable() {
        frame.addMouseMotionListener(resizableWindowListener);
    }

    private final MouseMotionListener resizableWindowListener = new MouseMotionListener() {

        @Override
        public void mouseMoved(MouseEvent e) {
            Point p = e.getPoint();
            int margin = 10;

            // Determine cursor based on mouse position
            if (isOnBottomRightCorner(p, margin)) {
                frame.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
            } else if (isOnRightEdge(p, margin)) {
                frame.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
            } else if (isOnBottomEdge(p, margin)) {
                frame.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
            } else {
                frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Point p = e.getPoint();

            // Resize the window based on active cursor
            switch (frame.getCursor().getType()) {
                case Cursor.E_RESIZE_CURSOR -> resizeWindow(p, "horizontal");
                case Cursor.S_RESIZE_CURSOR -> resizeWindow(p, "vertical");
                case Cursor.SE_RESIZE_CURSOR -> resizeWindow(p, "both");
                default -> {
                }
            }
        }

        private boolean isOnRightEdge(Point p, int margin) {
            return p.getX() >= frame.getWidth() - margin && p.getX() <= frame.getWidth();
        }

        private boolean isOnBottomEdge(Point p, int margin) {
            return p.getY() >= frame.getHeight() - margin && p.getY() <= frame.getHeight();
        }

        private boolean isOnBottomRightCorner(Point p, int margin) {
            return p.getX() >= frame.getWidth() - margin && p.getY() >= frame.getHeight() - margin;
        }

        private void resizeWindow(Point p, String direction) {
            int width = frame.getWidth();
            int height = frame.getHeight();
            int marginMajor, marginMinor;

            switch (direction) {
                case "horizontal" -> {
                    marginMajor = (int) (p.getX() - width);
                    marginMinor = (int) p.getX();
                    if (marginMajor > 0) {
                        frame.setBounds(frame.getX(), frame.getY(), width + marginMajor, height);
                    } else if (marginMinor >= 200) {
                        frame.setBounds(frame.getX(), frame.getY(), marginMinor, height);
                    }
                }

                case "vertical" -> {
                    marginMajor = (int) (p.getY() - height);
                    marginMinor = (int) p.getY();
                    if (marginMajor > 0) {
                        frame.setBounds(frame.getX(), frame.getY(), width, height + marginMajor);
                    } else if (marginMinor >= 200) {
                        frame.setBounds(frame.getX(), frame.getY(), width, marginMinor);
                    }
                }

                case "both" -> {
                    int marginMajorX = (int) (p.getX() - width);
                    int marginMajorY = (int) (p.getY() - height);
                    int marginMinorX = (int) p.getX();
                    int marginMinorY = (int) p.getY();

                    if (marginMajorX > 0 && marginMajorY > 0) {
                        frame.setBounds(frame.getX(), frame.getY(), width + marginMajorX, height + marginMajorY);
                    } else if (marginMinorX >= 200 && marginMinorY >= 200) {
                        frame.setBounds(frame.getX(), frame.getY(), marginMinorX, marginMinorY);
                    }
                }
            }
        }
    };
}
