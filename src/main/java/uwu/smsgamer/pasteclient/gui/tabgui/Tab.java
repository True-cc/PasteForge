/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package uwu.smsgamer.pasteclient.gui.tabgui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

//TODO: This is skidded PLS REMAKE
public class Tab<T> {
    @NotNull
    private List<SubTab<T>> subTabs = new ArrayList<>();
    private String text;

    public Tab(String text) {
        this.text = text;
    }

    public void addSubTab(SubTab<T> subTab) {
        subTabs.add(subTab);
    }

    @NotNull
    public List<SubTab<T>> getSubTabs() {
        return subTabs;
    }

    public void renderSubTabs(int x, int y, int selectedSubTab) {

        glTranslated(x, y, 0);

        FontRenderer font = Minecraft.getMinecraft().fontRenderer;

        int height = (font.FONT_HEIGHT + TabGui.OFFSET) * subTabs.size();

        int width = 0;

        for (SubTab<T> tab : subTabs) {
            if (font.getStringWidth(tab.getText()) > width) {
                width = font.getStringWidth(tab.getText());
            }
        }

        width += 2 + 2;

        TabGui.drawRect(GL_QUADS, 0, 0, width, height, TabGui.BACKGROUND.getRGB());

        glLineWidth(1.0f);
        TabGui.drawRect(GL_LINE_LOOP, 0, 0, width, height, TabGui.BORDER.getRGB());

        int offset = 2;

        int i = 0;

        for (SubTab<T> tab : subTabs) {
            if (selectedSubTab == i) {
                TabGui.drawRect(GL_QUADS, 0, offset - 2, width, offset + font.FONT_HEIGHT + TabGui.OFFSET - 1, TabGui.SELECTED.getRGB());
            }

            font.drawString(tab.getText(), 2, offset, TabGui.FOREGROUND.getRGB());
            offset += font.FONT_HEIGHT + TabGui.OFFSET;
            i++;
        }

        glTranslated(-x, -y, 0);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
