import React from "react";
import {
  Box, TextField, Button, Select, MenuItem, InputLabel, FormControl,
  Table, TableHead, TableRow, TableCell, TableBody, Chip
} from "@mui/material";

export default function App() {
  const dummyData = [
    {
      name: "A案件", min: 60, max: 70, location: "東京都",
      rate: 5, period: "202507", skills: "C#,Java,MySQL",
      flow: "2次請け", interview: 1, remote: "ハイブリッド", link: "http://..."
    },
    {
      name: "B案件", min: 60, max: 90, location: "神奈川県",
      rate: 5, period: "202507", skills: "AWS,Linux",
      flow: "2次請け", interview: 1, remote: "常駐", link: "http://..."
    }
  ];

  return (
    <Box p={4}>
      <h2>高度人工知能搭載SES案件検索サービス</h2>

      {/* ▼検索フォーム */}
      <Box display="flex" gap={2} flexWrap="wrap" mb={4}>
        <TextField label="単価下限" type="number" size="small" />
        <TextField label="単価上限" type="number" size="small" />
        <FormControl size="small" sx={{ minWidth: 150 }}>
          <InputLabel>勤務地</InputLabel>
          <Select label="勤務地">
            <MenuItem value=""><em>未選択</em></MenuItem>
            <MenuItem value="東京都">東京都</MenuItem>
            <MenuItem value="神奈川県">神奈川県</MenuItem>
          </Select>
        </FormControl>
        <FormControl size="small" sx={{ minWidth: 150 }}>
          <InputLabel>リモート可否</InputLabel>
          <Select label="リモート可否">
            <MenuItem value=""><em>未選択</em></MenuItem>
            <MenuItem value="ハイブリッド">ハイブリッド</MenuItem>
            <MenuItem value="常駐">常駐</MenuItem>
          </Select>
        </FormControl>
        <Button variant="contained">検索</Button>
      </Box>

      {/* ▼案件一覧表 */}
      <Table size="small" sx={{ minWidth: 1000 }}>
        <TableHead>
          <TableRow>
            <TableCell>案件名</TableCell>
            <TableCell>単価下限</TableCell>
            <TableCell>単価上限</TableCell>
            <TableCell>勤務地</TableCell>
            <TableCell>稼働率</TableCell>
            <TableCell>期間</TableCell>
            <TableCell>必須スキル</TableCell>
            <TableCell>商流</TableCell>
            <TableCell>面談回数</TableCell>
            <TableCell>リモート可否</TableCell>
            <TableCell>メールリンク</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {dummyData.map((row, i) => (
            <TableRow key={i}>
              <TableCell>{row.name}</TableCell>
              <TableCell>{row.min}</TableCell>
              <TableCell>{row.max}</TableCell>
              <TableCell>{row.location}</TableCell>
              <TableCell>{row.rate}</TableCell>
              <TableCell>{row.period}</TableCell>
              <TableCell>
                {row.skills.split(",").map((s, idx) => (
                  <Chip key={idx} label={s} size="small" sx={{ mr: 0.5 }} />
                ))}
              </TableCell>
              <TableCell>
                <Chip label={row.flow} color="error" size="small" />
              </TableCell>
              <TableCell>{row.interview}</TableCell>
              <TableCell>
                <Chip label={row.remote} color="success" size="small" />
              </TableCell>
              <TableCell>
                <a href={row.link} target="_blank" rel="noreferrer">リンク</a>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </Box>
  );
}
